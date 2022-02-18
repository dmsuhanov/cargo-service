package com.suhan.cargo.domain.location;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("location")
@Getter
@Setter
public class LocationEntity {

    @Id
    @Column("id")
    private UUID id;
    private String name;
    private String description;

}
