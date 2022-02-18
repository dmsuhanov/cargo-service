package com.suhan.cargo.controller;

import com.suhan.cargo.controller.model.LocationDto;
import com.suhan.cargo.domain.location.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {

    private final LocationRepository locationRepository;

    @GetMapping
    public Flux<LocationDto> getLocations() {
        return locationRepository.findAll().map(a -> new LocationDto(a.id().orElseThrow(), a.name(), a.description()));
    }

}
