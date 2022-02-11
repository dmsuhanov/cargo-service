package com.suhan.cargo.domain.cargo;

import com.suhan.cargo.domain.customer.Customer;
import com.suhan.cargo.domain.mapper.CustomerRoleMapper;
import com.suhan.cargo.domain.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.relational.core.query.Criteria.*;
import static org.springframework.data.relational.core.query.Query.*;

@Component
@RequiredArgsConstructor
public class CargoRepositoryImpl implements CargoRepository {

    private final R2dbcEntityTemplate template;
    private final CustomerRoleMapper customerRoleMapper;
    private final DatabaseClient databaseClient;

    @Override
    public Mono<Cargo> findById(UUID id) {
        return template.select(CargoEntity.class)
                .matching(query(where("id").is(id)))
                .one()
                .zipWith(
                        databaseClient.sql("select ccr.id, c.id as customer_id, c.name as customer_name, r.id as role_id, r.name as role_name \n" +
                                        "from cargo_customer_role as ccr\n" +
                                        "join customer as c on ccr.customer_id = c.id\n" +
                                        "join role as r on ccr.role_id = r.id\n" +
                                        "where ccr.cargo_id = :cargoId").bind("cargoId", id)
                                .map(customerRoleMapper::apply)
                                .all()
                                .collect(Collectors.toSet())
                )
                .map(tuple -> new Cargo(tuple.getT1().getId(), tuple.getT1().getCreateAt(), tuple.getT2()));
    }

    @Transactional
    @Override
    public Mono<Cargo> save(Cargo cargo) {
        if (cargo.id().isPresent()) {
            Update update = Update.from(new HashMap<>());
            if (cargo.unitCreateAt().isDirty()) {
                update = update.set("create_at", cargo.unitCreateAt().object());
            }
            Mono<Integer> resultUpdate;
            if (!update.getAssignments().isEmpty()) {
                resultUpdate = template.update(CargoEntity.class)
                        .matching(query(where("id").is(cargo.id().get())))
                        .apply(update);
            } else {
                resultUpdate = Mono.empty();
            }
            if (cargo.unitCustomerRoles().isDirty()) {
                Mono<Integer> resultDelete = template.delete(CargoCustomerRoleEntity.class)
                        .matching(query(where("cargo_id").is(cargo.id().get())))
                        .all();
                List<CargoCustomerRoleEntity> customerRoleEntities = new ArrayList<>();
                for (CustomerRole customerRole : cargo.unitCustomerRoles().object()) {
                    customerRoleEntities.add(new CargoCustomerRoleEntity(
                            null,
                            cargo.id().get(),
                            customerRole.customer().id().orElseThrow(),
                            customerRole.role().id().orElseThrow()));
                }
                List<Mono<CargoCustomerRoleEntity>> resultInsertList = new ArrayList<>();
                for (CargoCustomerRoleEntity customerRoleEntity : customerRoleEntities) {
                    resultInsertList.add(template.insert(CargoCustomerRoleEntity.class).using(customerRoleEntity));
                }
                Flux<CargoCustomerRoleEntity> resultInsert = Flux.concat(resultInsertList);
                return resultUpdate
                        .then(resultDelete)
                        .then(resultInsert.collectList())
                        .flatMap(a -> this.findById(cargo.id().get()));
            }
            return resultUpdate.map(a -> cargo);
        } else {
            final CargoEntity cargoEntity = new CargoEntity();
            cargoEntity.setCreateAt(cargo.unitCreateAt().object());
            return template.insert(CargoEntity.class)
                    .using(cargoEntity)
                    .map(saved -> new Cargo(saved.getId(), saved.getCreateAt(), new HashSet<>()));//TODO
        }

    }

    private void updateCustomRoles(UUID cargoId) {

    }

//    @Override
//    public Mono<Cargo> save(Cargo cargo) {
//        final CargoEntity cargoEntity;
//        if (cargo.id().isPresent()) {
//            return cargoDao.findById(cargo.id().get())
//                    .flatMap(exist -> {
//                        exist.setCreateAt(cargo.createAt());
//                        return cargoDao.save(exist);
//                    }).map(a -> new Cargo(a.getId(), a.getCreateAt(), new HashMap<>(), null, new DeliveryHistory(new ArrayList<>())));
//        } else {
//            cargoEntity = new CargoEntity();
//            cargoEntity.setCreateAt(cargo.createAt());
//            return cargoDao.save(cargoEntity).map(saved -> new Cargo(saved.getId(), saved.getCreateAt(), new HashMap<>(), null, new DeliveryHistory(new ArrayList<>())));
//        }
//
//    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return template.delete(CargoEntity.class)
                .matching(query(where("id").is(id)))
                .all()
                .flatMap(a -> Mono.empty());
    }

}
