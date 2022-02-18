package com.suhan.cargo.service.impl;

import com.suhan.cargo.controller.model.CargoFormDto;
import com.suhan.cargo.domain.DeliverySpecification;
import com.suhan.cargo.domain.cargo.Cargo;
import com.suhan.cargo.domain.CargoFactory;
import com.suhan.cargo.domain.cargo.CargoRepository;
import com.suhan.cargo.domain.cargo.CustomerRole;
import com.suhan.cargo.domain.customer.Customer;
import com.suhan.cargo.domain.customer.CustomerRepository;
import com.suhan.cargo.domain.location.Location;
import com.suhan.cargo.domain.location.LocationRepository;
import com.suhan.cargo.domain.role.Role;
import com.suhan.cargo.domain.role.RoleRepository;
import com.suhan.cargo.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;
    private final CargoFactory cargoFactory;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    private final LocationRepository locationRepository;


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
    public Mono<Cargo> booking(CargoFormDto cargoFormDto) {
        Mono<Cargo> cargo = Mono.zip(
                customerRepository.findById(cargoFormDto.getCustomerSenderId()),
                roleRepository.sender(),
                customerRepository.findById(cargoFormDto.getCustomerRecipientId()),
                roleRepository.recipient(),
                locationRepository.findById(cargoFormDto.getLocationToId())
        ).map(zip -> {
            final CustomerRole sender = new CustomerRole(zip.getT1(), zip.getT2());
            final CustomerRole recipient = new CustomerRole(zip.getT3(), zip.getT4());
            final DeliverySpecification goal = new DeliverySpecification(zip.getT5());
            return new Cargo(Set.of(sender, recipient), goal);
        });
        return cargoRepository.create(cargo);
    }

}
