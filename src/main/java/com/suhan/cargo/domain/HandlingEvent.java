package com.suhan.cargo.domain;

import com.suhan.cargo.domain.cargo.Cargo;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Манипуляция.
 */
public final class HandlingEvent extends AssertionConcern implements Entity {

    private final UUID id;
    private final Cargo cargo;
    private final LocalDateTime dateTime;
    private final CarrierMovement carrierMovement;

    public HandlingEvent(UUID id, Cargo cargo, LocalDateTime dateTime, CarrierMovement carrierMovement) {
        this.assertArgumentNotNull(cargo, "The cargo must not be null.");
        this.assertArgumentNotNull(dateTime, "The dateTime must not be null.");
        this.assertArgumentNotNull(carrierMovement, "The carrierMovement must not be null.");
        this.id = id;
        this.cargo = cargo;
        this.dateTime = dateTime;
        this.carrierMovement = carrierMovement;
    }

    public Optional<UUID> id() {
        return Optional.ofNullable(id);
    }

    public Cargo cargo() {
        return cargo;
    }

    public LocalDateTime dateTime() {
        return dateTime;
    }

    public CarrierMovement carrierMovement() {
        return carrierMovement;
    }
}
