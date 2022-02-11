package com.suhan.cargo.domain.mapper;

import com.suhan.cargo.domain.cargo.CustomerRole;
import com.suhan.cargo.domain.customer.Customer;
import com.suhan.cargo.domain.role.Role;
import io.r2dbc.spi.Row;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class CustomerRoleMapper implements BiFunction<Row, Object, CustomerRole> {

    @Override
    public CustomerRole apply(Row row, Object o) {
        UUID customerId = row.get("customer_id", UUID.class);
        String customerName = row.get("customer_name", String.class);
        UUID roleId = row.get("role_id", UUID.class);
        String roleName = row.get("role_name", String.class);
        return new CustomerRole(new Customer(customerId, customerName), new Role(roleId, roleName));
    }

}
