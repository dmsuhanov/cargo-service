package com.suhan.cargo.domain.role;

import com.suhan.cargo.domain.customer.Customer;
import com.suhan.cargo.domain.customer.CustomerEntity;
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
public class RoleRepositoryImpl implements RoleRepository {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<Role> save(Role role) {
        if (role.id().isPresent()) {
            Update update = Update.from(new HashMap<>());
            if (role.unitName().isDirty()) {
                update = update.set("name", role.unitName().object());
            }
            if (!update.getAssignments().isEmpty()) {
                return template.update(RoleEntity.class)
                        .matching(Query.query(Criteria.where("id").is(role.id().get())))
                        .apply(update)
                        .map(a -> role);
            }
            return Mono.just(role);
        } else {
            final RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(role.unitName().object());
            return template.insert(RoleEntity.class)
                    .using(roleEntity)
                    .map(saved -> new Role(saved.getId(), saved.getName()));
        }
    }

    @Override
    public Mono<Role> findById(UUID roleId) {
        return template.select(RoleEntity.class)
                .matching(Query.query(Criteria.where("id").is(roleId)))
                .one()
                .map(cargoEntity -> new Role(cargoEntity.getId(), cargoEntity.getName()));
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return template.delete(RoleEntity.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .all()
                .flatMap(a -> Mono.empty());
    }

    @Override
    public Mono<Role> sender() {
        return template.select(RoleEntity.class)
                .matching(Query.query(Criteria.where("name").is("sender")))
                .one()
                .map(cargoEntity -> new Role(cargoEntity.getId(), cargoEntity.getName()));
    }

    @Override
    public Mono<Role> recipient() {
        return template.select(RoleEntity.class)
                .matching(Query.query(Criteria.where("name").is("recipient")))
                .one()
                .map(cargoEntity -> new Role(cargoEntity.getId(), cargoEntity.getName()));
    }

    @Override
    public Flux<Role> findAll() {
        return template.select(RoleEntity.class)
                .all()
                .map(roleEntity -> new Role(roleEntity.getId(), roleEntity.getName()));
    }

}
