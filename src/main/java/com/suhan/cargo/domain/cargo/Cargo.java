package com.suhan.cargo.domain.cargo;

import com.suhan.cargo.domain.*;
import com.suhan.cargo.domain.customer.Customer;
import com.suhan.cargo.domain.location.Location;
import com.suhan.cargo.domain.role.Role;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Груз.
 */
public final class Cargo extends AssertionConcern implements Entity {

    private final UUID id;
    private final UnitOfWork<LocalDateTime> createAt;
    private final UnitOfWork<Set<CustomerRole>> customerRoles;
    private final UnitOfWork<DeliverySpecification> goal;
//    private final DeliveryHistory deliveryHistory;

    public Cargo(Set<CustomerRole> customerRoles, DeliverySpecification deliverySpecification) {
        this(null,
                new UnitOfWork<>(LocalDateTime.now(), true),
                new UnitOfWork<>(customerRoles, true),
                new UnitOfWork<>(deliverySpecification, true));
    }

    Cargo(UUID id, UnitOfWork<LocalDateTime> createAt, UnitOfWork<Set<CustomerRole>> customerRoles,
                  UnitOfWork<DeliverySpecification> goal) {
        this.assertArgumentNotNull(createAt.object(), "The createAt must not be null.");
        this.assertArgumentNotNull(customerRoles.object(), "The createAt must not be null.");
        this.assertArgumentNotNull(goal.object(), "The goal must not be null.");
        this.id = id;
        this.createAt = createAt;
        this.customerRoles = customerRoles;
        this.goal = goal;
    }

    @Override
    public Optional<UUID> id() {
        return Optional.ofNullable(id);
    }

    public Cargo addCustomerWithRole(Customer customer, Role role) {
        Set<CustomerRole> customerRoleSet = new HashSet<>(customerRoles.object());
        customerRoleSet.add(new CustomerRole(customer, role));
        return new Cargo(id, createAt, new UnitOfWork<>(customerRoleSet, true), goal);
    }

    public Cargo goal(Location location) {
        return null;
    }

//    public Optional<DeliverySpecification> goal() {
//        return Optional.ofNullable(goal);
//    }
//
//    public DeliveryHistory deliveryHistory() {
//        return deliveryHistory;
//    }
//
//    public Map<Role, Customer> customerRoles() {
//        return customerRoles;
//    }
//
//    public Cargo changeGoalLocation(Location newLocation) {
//        return new Cargo(id, createAt, customerRoles, new DeliverySpecification(newLocation, goal.dateTime()), deliveryHistory);
//    }
//
//    public Cargo addSender(Customer customer) {
//        final Map<Role, Customer> newMap = new HashMap<>(customerRoles);
//        newMap.put(Role.SENDER, customer);
//        return new Cargo(id, createAt, newMap, goal, deliveryHistory);
//    }
//
//    public Optional<Customer> sender() {
//        return Optional.ofNullable(customerRoles.get(Role.SENDER));
//    }
//
//    public Cargo addRecipient(Customer customer) {
//        final Map<Role, Customer> newMap = new HashMap<>(customerRoles);
//        newMap.put(Role.RECIPIENT, customer);
//        return new Cargo(id, createAt, newMap, goal, deliveryHistory);
//    }
//
//    public Optional<Customer> recipient() {
//        return Optional.ofNullable(customerRoles.get(Role.RECIPIENT));
//    }

    UnitOfWork<LocalDateTime> unitCreateAt() {
        return createAt;
    }

    UnitOfWork<Set<CustomerRole>> unitCustomerRoles() {
        return customerRoles;
    }

    UnitOfWork<DeliverySpecification> unitDeliverySpecification() {
        return goal;
    }
}
