package com.suhan.cargo.domain.cargo;

import com.suhan.cargo.domain.DeliverySpecification;
import com.suhan.cargo.domain.UnitOfWork;
import com.suhan.cargo.domain.cargo.Cargo;
import com.suhan.cargo.domain.customer.Customer;
import com.suhan.cargo.domain.location.Location;
import com.suhan.cargo.domain.location.LocationEntity;
import com.suhan.cargo.domain.role.Role;
import liquibase.pro.packaged.U;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class CargoTest {

    @Test
    public void shouldCreateNewCargo() {
        //Given создаем новый экземпляр груза
        final Customer sender = new Customer(UUID.randomUUID(), "sender");
        final Customer recipient = new Customer(UUID.randomUUID(), "recipient");
        final Role senderRole = new Role(UUID.randomUUID(), "sender role");
        final Role recipientRole = new Role(UUID.randomUUID(), "recipient role");
        final Location location = new Location(UUID.randomUUID(), "location name", "location desc");
        final Cargo cargo = new Cargo(UUID.randomUUID(),
                new UnitOfWork<>(LocalDateTime.now(), false),
                new UnitOfWork<>(Set.of(new CustomerRole(sender, senderRole), new CustomerRole(recipient, recipientRole)), false),
                new UnitOfWork<>(new DeliverySpecification(location), false));
        //When проверяем состояние нового объекта
        Optional<UUID> id = cargo.id();
        Set<CustomerRole> roleCustomerMap = cargo.unitCustomerRoles().object();
//        DeliveryHistory deliveryHistory = cargo.deliveryHistory();
        DeliverySpecification goal = cargo.unitDeliverySpecification().object();
//        LocalDateTime at = cargo.;
//        Map<Role, Customer> roleCustomerMap = cargo.customerRoles();
        //Then данные должны соответсвовать
        assertThat(id).isPresent();
        assertThat(roleCustomerMap).size().isEqualTo(2);
//        assertThat(deliveryHistory).isEqualTo(new DeliveryHistory(new ArrayList<>()));
//        assertThat(goal).isEmpty();
//        assertThat(at).isNotNull();
//        assertThat(roleCustomerMap).isNotNull().isEmpty();
    }

//    @Test
//    public void shouldAddCustomerRoleWhenAddToCargo() {
//        //Given создаем новый экземпляр груза
//        Cargo cargo = new Cargo();
//        //When вызываем метод добавления пользователя с ролью
//        cargo = cargo.addCustomerWithRole(new Customer("ashda"), new Role("asdasd"));
//        //Then к грузу должен добавиться пользователь с ролью
//        assertThat(cargo.unitCustomerRoles().object().size()).isEqualTo(1);
//    }
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