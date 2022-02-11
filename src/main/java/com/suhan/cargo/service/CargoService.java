package com.suhan.cargo.service;

import com.suhan.cargo.domain.cargo.Cargo;
import reactor.core.publisher.Mono;

/**
 * Сервис для работы с грузами.
 */
public interface CargoService {

//    /**
//     * Изменение локации назначения груза.
//     *
//     * @param cargoId    идентификатор груза
//     * @param locationId идентификатор локации
//     * @return обновленный заказ
//     */
//    Mono<Cargo> changeLocation(UUID cargoId, UUID locationId);
//
//    /**
//     * Создать новый груз, на основе существующего.
//     *
//     * @param cargoId идентификатор груза
//     * @return новый груз
//     */
//    Mono<Cargo> copyPrototype(UUID cargoId);

    /**
     * Создание нового груза.
     *
     * @return новый груз
     */
    Mono<Cargo> createNew();

}
