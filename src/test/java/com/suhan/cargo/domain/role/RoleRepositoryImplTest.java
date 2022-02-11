//package com.suhan.cargo.domain.role;
//
//import com.suhan.cargo.domain.customer.Customer;
//import com.suhan.cargo.domain.customer.CustomerRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//class RoleRepositoryImplTest {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Test
//    public void shouldSaveCustomer() {
//        //Given создаем роль
//        final Role role = new Role("testRole");
//        //When вызываем метод сохранения клиента
//        final Role saved = roleRepository.save(role).block();
//        //Then получаем сохраненную роль
//        assertThat(saved).isNotNull();
//        assertThat(saved.id()).isPresent();
//        final Role byId = roleRepository.findById(saved.id().get()).block();
//        assertThat(saved.name()).isEqualTo(role.name()).isEqualTo(byId.name());
//        roleRepository.deleteById(saved.id().get()).block();
//    }
//
//}