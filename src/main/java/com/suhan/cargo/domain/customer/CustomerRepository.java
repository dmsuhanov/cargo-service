package com.suhan.cargo.domain.customer;

import com.suhan.cargo.controller.model.CustomerDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Репозиторий клиента.
 */
public interface CustomerRepository {

    /**
     * Сохранение клиента.
     *
     * @param customer клиент
     * @return сохраненный клиент
     */
    Mono<Customer> save(Customer customer);

    /**
     * Поиск клиента по идентификатору.
     *
     * @param customerId идентификатор клиента
     * @return клиент
     */
    Mono<Customer> findById(UUID customerId);

    /**
     * Удаление клиента по идентификатору.
     *
     * @param id идентификатор клиента
     * @return void
     */
    Mono<Void> deleteById(UUID id);

    /**
     * Поиск всех клиентов.
     *
     * @return клиенты
     */
    Flux<Customer> findAll();

}
