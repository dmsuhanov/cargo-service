package com.suhan.cargo.domain.customer;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CustomerTest {

    @Test
    public void shouldCreateNewCargo() {
        //Given создаем нового пользователя
        final Customer customer = new Customer("name_customer");
        //When проверяем состояние нового объекта
        Optional<UUID> id = customer.id();
        String name = customer.name();
        //Then данные должны соответсвовать
        assertThat(id).isEmpty();
        assertThat(name).isEqualTo("name_customer");
    }

}