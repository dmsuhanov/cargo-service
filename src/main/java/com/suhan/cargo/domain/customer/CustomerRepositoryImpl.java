package com.suhan.cargo.domain.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<Customer> save(Customer customer) {
        if (customer.id().isPresent()) {
            Update update = Update.from(new HashMap<>());
            if (customer.unitName().isDirty()) {
                update = update.set("name", customer.unitName().object());
            }
            if (!update.getAssignments().isEmpty()) {
                return template.update(CustomerEntity.class)
                        .matching(Query.query(Criteria.where("id").is(customer.id().get())))
                        .apply(update)
                        .map(a -> customer);
            }
            return Mono.just(customer);
        } else {
            final CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setName(customer.unitName().object());
            return template.insert(CustomerEntity.class)
                    .using(customerEntity)
                    .map(saved -> new Customer(saved.getId(), saved.getName()));
        }
    }

    @Override
    public Mono<Customer> findById(UUID customerId) {
        return template.select(CustomerEntity.class)
                .matching(Query.query(Criteria.where("id").is(customerId)))
                .one()
                .map(cargoEntity -> new Customer(cargoEntity.getId(), cargoEntity.getName()));
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return template.delete(CustomerEntity.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .all()
                .flatMap(a -> Mono.empty());
    }

    @Override
    public Flux<Customer> findAll() {
        return template.select(CustomerEntity.class)
                .all()
                .map(cargoEntity -> new Customer(cargoEntity.getId(), cargoEntity.getName()));
    }

}
