package com.suhan.cargo.domain;

import java.util.Optional;
import java.util.UUID;

/**
 * Интерфейс для сущности.
 */
public interface Entity {

    Optional<UUID> id();

}
