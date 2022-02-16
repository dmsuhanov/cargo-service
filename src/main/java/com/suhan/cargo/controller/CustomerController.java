package com.suhan.cargo.controller;

import com.suhan.cargo.controller.model.CustomerDto;
import com.suhan.cargo.controller.model.CustomerFormDto;
import com.suhan.cargo.domain.customer.Customer;
import com.suhan.cargo.domain.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;

    @GetMapping
    public Flux<CustomerDto> getUsers() {
        return customerRepository.findAll().map(a -> new CustomerDto(a.id().orElseThrow(), a.name()));
    }

    @PostMapping
    public Mono<CustomerDto> create(@RequestBody CustomerFormDto customerForm) {
        final Customer customer = new Customer(customerForm.getName());
        return customerRepository.save(customer)
                .map(a -> new CustomerDto(a.id().orElseThrow(), a.name()));
    }

}
