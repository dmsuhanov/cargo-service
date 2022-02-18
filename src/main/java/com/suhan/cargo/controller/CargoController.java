package com.suhan.cargo.controller;

import com.suhan.cargo.controller.model.CargoDto;
import com.suhan.cargo.controller.model.CargoFormDto;
import com.suhan.cargo.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cargo")
@RequiredArgsConstructor
public class CargoController {

    private final CargoService cargoService;

    @PostMapping
    public Mono<CargoDto> create(@RequestBody CargoFormDto cargoFormDto) {
        return cargoService.booking(cargoFormDto).map(a -> new CargoDto(a.id().orElseThrow()));
    }

}
