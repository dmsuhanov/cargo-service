package com.suhan.cargo.domain.role;

import io.micrometer.core.instrument.binder.db.MetricsDSLContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Репозиторий клиента.
 */
public interface RoleRepository {

    /**
     * Сохранение роли.
     *
     * @param role роль
     * @return сохраненная роль
     */
    Mono<Role> save(Role role);

    /**
     * Поиск роли по идентификатору.
     *
     * @param roleId идентификатор роли
     * @return роль
     */
    Mono<Role> findById(UUID roleId);

    /**
     * Удаление роли по идентификатору.
     *
     * @param id идентификатор роли
     * @return void
     */
    Mono<Void> deleteById(UUID id);

    /**
     * Получение роли отправитель.
     *
     * @return роль
     */
    Mono<Role> sender();

    /**
     * Получение роли получатель.
     *
     * @return роль
     */
    Mono<Role> recipient();

    /**
     * Получение всех ролей.
     *
     * @return роли
     */
    Flux<Role> findAll();
}
