package com.suhan.cargo.domain;

import java.util.Optional;
import java.util.UUID;

/**
 * Местоположение.
 */
public final class Location extends AssertionConcern implements Entity {

    private final UUID id;
    private final String name;
    private final String description;

    public Location(UUID id, String name, String description) {
        this.id = id;
        this.assertArgumentNotEmpty(name, "The name must not be empty.");
        this.assertArgumentLength(name, 50, "The name must be 50 characters or less.");
        this.name = name;
        this.assertArgumentNotEmpty(description, "The description must not be empty.");
        this.assertArgumentLength(description, 400, "The description must be 400 characters or less.");
        this.description = description;
    }

    @Override
    public Optional<UUID> id() {
        return Optional.ofNullable(id);
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }
}
