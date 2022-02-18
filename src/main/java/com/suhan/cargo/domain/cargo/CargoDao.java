package com.suhan.cargo.domain.cargo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface CargoDao extends ReactiveCrudRepository<CargoEntity, UUID> {

}
