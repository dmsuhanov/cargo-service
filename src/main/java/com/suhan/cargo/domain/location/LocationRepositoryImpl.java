package com.suhan.cargo.domain.location;

import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.CriteriaDefinition;
import org.springframework.data.relational.core.query.Query;
import org.springframework.data.relational.core.query.Update;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LocationRepositoryImpl implements LocationRepository {

    private final R2dbcEntityTemplate template;

    @Override
    public Mono<Location> save(Location location) {
        if (location.id().isPresent()) {
            Update update = Update.from(new HashMap<>());
            if (location.unitName().isDirty()) {
                update = update.set("name", location.unitName().object());
            }
            if (location.unitDescription().isDirty()) {
                update = update.set("description", location.unitDescription().object());
            }
            if (!update.getAssignments().isEmpty()) {
                return template.update(LocationEntity.class)
                        .matching(Query.query(Criteria.where("id").is(location.id().get())))
                        .apply(update)
                        .map(a -> location);
            }
            return Mono.just(location);
        } else {
            final LocationEntity locationEntity = new LocationEntity();
            locationEntity.setName(location.unitName().object());
            locationEntity.setDescription(location.unitDescription().object());
            return template.insert(LocationEntity.class)
                    .using(locationEntity)
                    .map(saved -> new Location(saved.getId(), saved.getName(), saved.getDescription()));
        }
    }

    @Override
    public Flux<Location> findAll() {
        return template.select(LocationEntity.class)
                .all()
                .map(locationEntity -> new Location(locationEntity.getId(), locationEntity.getName(), locationEntity.getDescription()));
    }

    @Override
    public Mono<Location> findById(UUID locationId) {
        return template.select(LocationEntity.class)
                .matching(Query.query(Criteria.where("id").is(locationId)))
                .one()
                .map(locationEntity ->
                        new Location(locationEntity.getId(), locationEntity.getName(), locationEntity.getDescription()));
    }

}
