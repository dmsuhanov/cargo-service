package com.suhan.cargo.domain.cargo;

import com.suhan.cargo.domain.cargo.Cargo;
import com.suhan.cargo.domain.customer.Customer;
import com.suhan.cargo.domain.role.Role;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class CargoTest {

    @Test
    public void shouldCreateNewCargo() {
        //Given создаем новый экземпляр груза
        final Cargo cargo = new Cargo();
        //When проверяем состояние нового объекта
        Optional<UUID> id = cargo.id();
        Set<CustomerRole> roleCustomerMap = cargo.unitCustomerRoles().object();
//        DeliveryHistory deliveryHistory = cargo.deliveryHistory();
//        Optional<DeliverySpecification> goal = cargo.goal();
//        LocalDateTime at = cargo.;
//        Map<Role, Customer> roleCustomerMap = cargo.customerRoles();
        //Then данные должны соответсвовать
        assertThat(id).isEmpty();
        assertThat(roleCustomerMap).isEmpty();
//        assertThat(deliveryHistory).isEqualTo(new DeliveryHistory(new ArrayList<>()));
//        assertThat(goal).isEmpty();
//        assertThat(at).isNotNull();
//        assertThat(roleCustomerMap).isNotNull().isEmpty();
    }

    @Test
    public void shouldAddCustomerRoleWhenAddToCargo() {
        //Given создаем новый экземпляр груза
        Cargo cargo = new Cargo();
        //When вызываем метод добавления пользователя с ролью
        cargo = cargo.addCustomerWithRole(new Customer("ashda"), new Role("asdasd"));
        //Then к грузу должен добавиться пользователь с ролью
        assertThat(cargo.unitCustomerRoles().object().size()).isEqualTo(1);
    }
//
//    @Test
//    public void shouldAddSenderToCargo() {
//        //Given создаем новый экземпляр груза, пользователь
//        final Cargo cargo = new Cargo(new DeliveryHistory(new ArrayList<>()));
//        final Customer customer = new Customer(UUID.randomUUID(), "name");
//        //When вызываем метод добавления отправителя
//        Cargo cargoWithSender = cargo.addSender(customer);
//        //Then у груза появляется отправитель
//        assertThat(cargoWithSender.sender()).isNotNull();
//    }
//
//    @Test
//    public void shouldAddRecipientToCargo() {
//        //Given создаем новый экземпляр груза, пользователь
//        final Cargo cargo = new Cargo(new DeliveryHistory(new ArrayList<>()));
//        final Customer customer = new Customer(UUID.randomUUID(), "name");
//        //When вызываем метод добавления отправителя
//        Cargo cargoWithRecipient = cargo.addRecipient(customer);
//        //Then у груза появляется отправитель
//        assertThat(cargoWithRecipient.recipient()).isNotNull();
//    }

}