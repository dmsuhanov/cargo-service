package com.suhan.cargo.domain.cargo;

import com.suhan.cargo.domain.DeliverySpecification;
import com.suhan.cargo.domain.UnitOfWork;
import com.suhan.cargo.domain.location.Location;
import com.suhan.cargo.domain.mapper.CargoViewMapper;
import com.suhan.cargo.domain.mapper.CustomerRoleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Update;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.relational.core.query.Criteria.*;
import static org.springframework.data.relational.core.query.Query.*;

@Component
@RequiredArgsConstructor
public class CargoRepositoryImpl implements CargoRepository {

    private final R2dbcEntityTemplate template;
    private final CustomerRoleMapper customerRoleMapper;
    private final CargoViewMapper cargoViewMapper;
    private final DatabaseClient databaseClient;

    @Override
    public Mono<Cargo> findById(UUID id) {
        Mono<Set<CustomerRole>> customerRoles =
                databaseClient.sql("select ccr.id, c.id as customer_id, c.name as customer_name, r.id as role_id, r.name as role_name \n" +
                                "from cargo_customer_role as ccr\n" +
                                "join customer as c on ccr.customer_id = c.id\n" +
                                "join role as r on ccr.role_id = r.id\n" +
                                "where ccr.cargo_id = :cargoId").bind("cargoId", id)
                        .map(customerRoleMapper::apply)
                        .all()
                        .collect(Collectors.toSet());
        Mono<CargoView> cargoView =
                databaseClient.sql("select c.id as cargo_id, c.create_at as cargo_create_at, l.id as location_id, l.name as location_name, l.description as location_description " +
                                "from cargo as c\n" +
                                "join location as l on c.goal_location_id = l.id\n" +
                                "where c.id = :cargoId").bind("cargoId", id)
                        .map(cargoViewMapper::apply)
                        .one();
        return Mono.zip(cargoView, customerRoles)
                .map(zip -> new Cargo(
                        zip.getT1().getId(),
                        new UnitOfWork<>(zip.getT1().getCreateAt(), false),
                        new UnitOfWork<>(zip.getT2(), false),
                        new UnitOfWork<>(new DeliverySpecification(new Location(
                                zip.getT1().getGoalLocationId(),
                                zip.getT1().getGoalLocationName(),
                                zip.getT1().getGoalLocationDescription())), false)
                ));
    }

    @Transactional
    @Override
    public Mono<Cargo> create(Mono<Cargo> cargoMono) {
        return cargoMono
                .flatMap(cargo -> {
                    final CargoEntity cargoEntity = new CargoEntity();
                    cargoEntity.setCreateAt(cargo.unitCreateAt().object());
                    cargoEntity.setGoalLocationId(cargo.unitDeliverySpecification().object().unitLocation().object().id().orElseThrow());
                    Mono<CargoEntity> cargoEntityMono = template.insert(CargoEntity.class)
                            .using(cargoEntity);
                    return cargoEntityMono.flatMap(a -> {
                        Mono<List<CargoCustomerRoleEntity>> resultInsert =
                                this.insertCustomRoles(a.getId(), cargo.unitCustomerRoles().object());
                        return resultInsert
                                .then(findById(a.getId()));
                    });
                });
    }

    @Transactional
    @Override
    public Mono<Cargo> update(Mono<Cargo> cargoMono) {
        return cargoMono
                .flatMap(cargo -> {
                    Update update = Update.from(new HashMap<>());
                    if (cargo.unitCreateAt().isDirty()) {
                        update = update.set("create_at", cargo.unitCreateAt().object());
                    }
                    if (cargo.unitDeliverySpecification().isDirty()) {
                        update = update.set("goal_location_id", cargo.unitDeliverySpecification().object().unitLocation().object().id());
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
                        Mono<List<CargoCustomerRoleEntity>> resultInsertCustomRoles =
                                this.insertCustomRoles(cargo.id().get(), cargo.unitCustomerRoles().object());
                        return resultUpdate
                                .then(resultDelete)
                                .then(resultInsertCustomRoles)
                                .flatMap(a -> this.findById(cargo.id().get()));
                    }
                    return resultUpdate.flatMap(a -> findById(cargo.id().orElseThrow()));
                });
    }

    private Mono<List<CargoCustomerRoleEntity>> insertCustomRoles(UUID cargoId, Set<CustomerRole> customerRoles) {
        List<CargoCustomerRoleEntity> customerRoleEntities = new ArrayList<>();
        for (CustomerRole customerRole : customerRoles) {
            customerRoleEntities.add(new CargoCustomerRoleEntity(
                    null,
                    cargoId,
                    customerRole.customer().id().orElseThrow(),
                    customerRole.role().id().orElseThrow()));
        }
        List<Mono<CargoCustomerRoleEntity>> resultInsertList = new ArrayList<>();
        for (CargoCustomerRoleEntity customerRoleEntity : customerRoleEntities) {
            resultInsertList.add(template.insert(CargoCustomerRoleEntity.class).using(customerRoleEntity));
        }
        return Flux.concat(resultInsertList).collectList();
    }

    @Override
    public Mono<Void> deleteById(UUID id) {
        return template.delete(CargoEntity.class)
                .matching(query(where("id").is(id)))
                .all()
                .flatMap(a -> Mono.empty());
    }

}
