package com.suhan.cargo.domain.cargo;

import com.suhan.cargo.domain.cargo.CargoEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CargoDao extends ReactiveCrudRepository<CargoEntity, UUID> {

}
