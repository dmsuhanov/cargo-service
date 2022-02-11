package com.suhan.cargo.repository;

import com.suhan.cargo.domain.Location;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Репозиторий местоположения.
 */
public interface LocationRepository {

    /**
     * Поиск локации по идентификатору.
     *
     * @param locationId идентификатор локации
     * @return локация
     */
    Mono<Location> findById(UUID locationId);

}
