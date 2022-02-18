package com.suhan.cargo.domain.location;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


/**
 * Репозиторий локаций.
 */
public interface LocationRepository {

    /**
     * Сохранение местоположения.
     *
     * @param location местоположение
     * @return сохраненное местоположение
     */
    Mono<Location> save(Location location);

    /**
     * Получение всех местоположений.
     *
     * @return местоположения
     */
    Flux<Location> findAll();

    /**
     * Поиск местоположения по идентификатору.
     *
     * @param locationId идентификатор локации
     * @return найденная локация
     */
    Mono<Location> findById(UUID locationId);
}
