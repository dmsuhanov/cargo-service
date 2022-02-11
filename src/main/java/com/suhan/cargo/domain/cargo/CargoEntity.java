package com.suhan.cargo.domain.cargo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("cargo")
@Getter
@Setter
public class CargoEntity {

    @Id
    @Column("id")
    private UUID id;
    @Column("create_at")
    private LocalDateTime createAt;

}
