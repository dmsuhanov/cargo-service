package com.suhan.cargo.domain.role;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class RoleTest {

    @Test
    public void shouldCreateNewCargo() {
        //Given создаем нового пользователя
        final Role role = new Role("name_role");
        //When проверяем состояние нового объекта
        Optional<UUID> id = role.id();
        String name = role.name();
        //Then данные должны соответсвовать
        assertThat(id).isEmpty();
        assertThat(name).isEqualTo("name_role");
    }

}