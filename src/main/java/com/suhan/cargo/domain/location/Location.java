package com.suhan.cargo.domain.location;

import com.suhan.cargo.domain.AssertionConcern;
import com.suhan.cargo.domain.Entity;
import com.suhan.cargo.domain.UnitOfWork;

import java.util.Optional;
import java.util.UUID;

/**
 * Местоположение.
 */
public final class Location extends AssertionConcern implements Entity {

    private final UUID id;
    private final UnitOfWork<String> name;
    private final UnitOfWork<String> description;

    public Location(UUID id, String name, String description) {
        this(id, new UnitOfWork<>(name, false), new UnitOfWork<>(description, false));
    }

    Location(UUID id, UnitOfWork<String> name, UnitOfWork<String> description) {
        this.assertArgumentNotEmpty(name.object(), "The name must not be empty.");
        this.assertArgumentLength(name.object(), 50, "The name must be 50 characters or less.");
        this.assertArgumentNotEmpty(description.object(), "The description must not be empty.");
        this.assertArgumentLength(description.object(), 400, "The description must be 400 characters or less.");
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public Optional<UUID> id() {
        return Optional.ofNullable(id);
    }

    public String name() {
        return name.object();
    }

    public String description() {
        return description.object();
    }

    UnitOfWork<String> unitName() {
        return name;
    }

    UnitOfWork<String> unitDescription() {
        return description;
    }
}
