package com.suhan.cargo.domain.cargo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CargoView {

    private UUID id;
    private LocalDateTime createAt;
    private UUID goalLocationId;
    private String goalLocationName;
    private String goalLocationDescription;

}
