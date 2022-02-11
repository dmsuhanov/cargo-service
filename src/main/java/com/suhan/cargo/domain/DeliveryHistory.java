package com.suhan.cargo.domain;

import java.util.List;
import java.util.Objects;

/**
 * История доставки.
 */
public final class DeliveryHistory extends AssertionConcern implements ValueObject {

    private final List<HandlingEvent> handlingEvents;

    public DeliveryHistory(List<HandlingEvent> handlingEvents) {
        this.assertArgumentNotNull(handlingEvents, "The handlingEvents must not be null.");
        this.handlingEvents = handlingEvents;
    }

    public List<HandlingEvent> handlingEvents() {
        return handlingEvents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryHistory that = (DeliveryHistory) o;
        return Objects.equals(handlingEvents, that.handlingEvents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(handlingEvents);
    }

}
