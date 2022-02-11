package com.suhan.cargo.repository.impl;

import com.suhan.cargo.domain.Location;
import com.suhan.cargo.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class LocationRepositoryImpl implements LocationRepository {

    @Override
    public Mono<Location> findById(UUID locationId) {
        return null;
    }
}
