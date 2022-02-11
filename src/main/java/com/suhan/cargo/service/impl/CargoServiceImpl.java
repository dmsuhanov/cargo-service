package com.suhan.cargo.service.impl;

import com.suhan.cargo.domain.cargo.Cargo;
import com.suhan.cargo.domain.CargoFactory;
import com.suhan.cargo.domain.cargo.CargoRepository;
import com.suhan.cargo.repository.LocationRepository;
import com.suhan.cargo.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;
    private final LocationRepository locationRepository;
    private final CargoFactory cargoFactory;

//    @Override
//    public Mono<Cargo> changeLocation(UUID cargoId, UUID locationId) {
//        return cargoRepository
//                .findById(cargoId)
//                .doOnError(a -> {
//                    throw new IllegalArgumentException(a.getMessage());
//                })
//                .zipWith(locationRepository.findById(locationId)
//                        .doOnError(a -> {
//                            throw new IllegalArgumentException(a.getMessage());
//                        }))
//                .map(tuple -> tuple.getT1().changeGoalLocation(tuple.getT2()));
//    }
//
//    @Override
//    public Mono<Cargo> copyPrototype(UUID cargoId) {
//        return cargoRepository
//                .findById(cargoId)
//                .doOnError(a -> {
//                    throw new IllegalArgumentException(a.getMessage());
//                })
//                .flatMap(cargo -> {
//                    Cargo newCargo = new Cargo(null, cargo.goal(), new DeliveryHistory(new ArrayList<>()));
//                    return cargoRepository.save(newCargo);
//                });
//    }

    @Override
    public Mono<Cargo> createNew() {
        return cargoRepository.save(cargoFactory.createNew());
    }

}
