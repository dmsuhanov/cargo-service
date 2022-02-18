package com.suhan.cargo.domain;

import com.suhan.cargo.domain.location.Location;

/**
 * Задание на доставку.
 */
public final class DeliverySpecification extends AssertionConcern implements ValueObject {

    private final UnitOfWork<Location> location;

    public DeliverySpecification(Location location) {
        this(new UnitOfWork<>(location, false));
    }

    public DeliverySpecification(UnitOfWork<Location> location) {
        this.assertArgumentNotNull(location, "The location must not be null.");
        this.location = location;
    }

    public UnitOfWork<Location> unitLocation() {
        return location;
    }

}
