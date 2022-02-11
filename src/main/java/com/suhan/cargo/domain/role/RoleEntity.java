package com.suhan.cargo.domain.role;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("role")
@Getter
@Setter
public class RoleEntity {

    @Id
    @Column("id")
    private UUID id;
    private String name;

}
