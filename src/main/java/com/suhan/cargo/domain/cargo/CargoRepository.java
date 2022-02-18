package com.suhan.cargo.domain.cargo;

import com.suhan.cargo.domain.cargo.Cargo;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Репозиторий грузов.
 */
public interface CargoRepository {

    /**
     * Поиск груза по идентификатору.
     *
     * @param id идентификатор груза
     * @return груз
     */
    Mono<Cargo> findById(UUID id);

    /**
     * Сохранение нового груза.
     *
     * @param cargo груз
     * @return сохраненный груз
     */
    Mono<Cargo> create(Mono<Cargo> cargo);

    /**
     * Обновление нового груза.
     *
     * @param cargo груз
     * @return сохраненный груз
     */
    Mono<Cargo> update(Mono<Cargo> cargo);

    /**
     * Удаление груза по идентификатору.
     *
     * @param id идентификатор
     * @return void
     */
    Mono<Void> deleteById(UUID id);

}
