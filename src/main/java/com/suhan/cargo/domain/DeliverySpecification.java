package com.suhan.cargo.domain;

import java.time.LocalDateTime;

/**
 * Задание на доставку.
 */
public final class DeliverySpecification extends AssertionConcern implements ValueObject {

    private final Location location;
    private final LocalDateTime dateTime;

    public DeliverySpecification(Location location, LocalDateTime dateTime) {
        this.assertArgumentNotNull(location, "The location must not be null.");
        this.assertArgumentNotNull(dateTime, "The dateTime must not be null.");
        this.location = location;
        this.dateTime = dateTime;
    }

    public Location location() {
        return location;
    }

    public LocalDateTime dateTime() {
        return dateTime;
    }

}
