package com.suhan.cargo.domain.cargo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("cargo_customer_role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CargoCustomerRoleEntity {

    @Id
    @Column("id")
    private UUID id;
    @Column("cargo_id")
    private UUID cargoId;
    @Column("customer_id")
    private UUID customerId;
    @Column("role_id")
    private UUID roleId;

}
