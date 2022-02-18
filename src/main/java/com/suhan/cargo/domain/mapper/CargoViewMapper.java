package com.suhan.cargo.domain.mapper;

import com.suhan.cargo.domain.cargo.CargoView;
import io.r2dbc.spi.Row;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class CargoViewMapper implements BiFunction<Row, Object, CargoView> {

    @Override
    public CargoView apply(Row row, Object o) {
        UUID cargoId = row.get("cargo_id", UUID.class);
        LocalDateTime createAt = row.get("cargo_create_at", LocalDateTime.class);
        UUID locationId = row.get("location_id", UUID.class);
        String locationName = row.get("location_name", String.class);
        String locationDesc = row.get("location_description", String.class);
        return new CargoView(cargoId, createAt, locationId, locationName, locationDesc);
    }

}
