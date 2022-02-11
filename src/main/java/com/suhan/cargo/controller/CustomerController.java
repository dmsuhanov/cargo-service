package com.suhan.cargo.controller;

import com.suhan.cargo.controller.model.CustomerDto;
import com.suhan.cargo.domain.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping
    public Flux<CustomerDto> getUsers() {
        return customerRepository.findAll().map(a -> new CustomerDto(a.id().orElseThrow(), a.name()));
    }

}
