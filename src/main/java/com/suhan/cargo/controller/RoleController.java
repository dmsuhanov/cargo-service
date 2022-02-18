package com.suhan.cargo.controller;

import com.suhan.cargo.controller.model.RoleDto;
import com.suhan.cargo.domain.role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;

    @GetMapping
    public Flux<RoleDto> getRoles() {
        return roleRepository.findAll().map(a -> new RoleDto(a.id().orElseThrow(), a.name()));
    }

}
