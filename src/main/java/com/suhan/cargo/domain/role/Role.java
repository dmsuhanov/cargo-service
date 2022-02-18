package com.suhan.cargo.domain.role;

import com.suhan.cargo.domain.AssertionConcern;
import com.suhan.cargo.domain.Entity;
import com.suhan.cargo.domain.UnitOfWork;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Роль.
 */
public final class Role extends AssertionConcern implements Entity {

    private final UUID id;
    private final UnitOfWork<String> name;

    public Role(String name) {
        this(null, name);
    }

    public Role(UUID id, String name) {
        this(id, new UnitOfWork<>(name, false));
    }

    Role(UUID id, UnitOfWork<String> name) {
        this.assertArgumentNotEmpty(name.object(), "The name must not be empty.");
        this.assertArgumentLength(name.object(), 50, "The name must be 50 characters or less.");
        this.id = id;
        this.name = name;
    }

    @Override
    public Optional<UUID> id() {
        return Optional.ofNullable(id);
    }

    public String name() {
        return name.object();
    }

    UnitOfWork<String> unitName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(name.object(), role.name.object());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name.object());
    }
}
