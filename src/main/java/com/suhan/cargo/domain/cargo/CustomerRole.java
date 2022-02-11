package com.suhan.cargo.domain.cargo;

import com.suhan.cargo.domain.AssertionConcern;
import com.suhan.cargo.domain.ValueObject;
import com.suhan.cargo.domain.customer.Customer;
import com.suhan.cargo.domain.role.Role;

import java.util.Objects;

/**
 * Пользователь с ролью.
 */
public final class CustomerRole extends AssertionConcern implements ValueObject {

    private final Customer customer;
    private final Role role;

    public CustomerRole(Customer customer, Role role) {
        this.assertArgumentNotNull(customer, "The customer must not be null.");
        this.assertArgumentNotNull(role, "The role must not be null.");
        this.customer = customer;
        this.role = role;
    }

    public Customer customer() {
        return customer;
    }

    public Role role() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerRole that = (CustomerRole) o;
        return Objects.equals(customer, that.customer) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, role);
    }

}
