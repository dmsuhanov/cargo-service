package com.suhan.cargo.domain;

import java.util.Optional;
import java.util.UUID;

/**
 * Переезд.
 */
public final class CarrierMovement extends AssertionConcern implements Entity {

    private final UUID id;
    private final Location location;
    private final CarrierMovementType carrierMovementType;

    public CarrierMovement(UUID id, Location location, CarrierMovementType carrierMovementType) {
        this.assertArgumentNotNull(location, "The location must not be null.");
        this.assertArgumentNotNull(carrierMovementType, "The carrierMovementType must not be null.");
        this.id = id;
        this.location = location;
        this.carrierMovementType = carrierMovementType;
    }

    @Override
    public Optional<UUID> id() {
        return Optional.ofNullable(id);
    }

    public Location location() {
        return location;
    }

    public CarrierMovementType carrierMovementType() {
        return carrierMovementType;
    }

}
