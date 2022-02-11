//package com.suhan.cargo.domain.customer;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//class CustomerRepositoryImplTest {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Test
//    public void shouldSaveCustomer() {
//        //Given создаем клиента
//        final Customer customer = new Customer("testName");
//        //When вызываем метод сохранения клиента
//        final Customer saved = customerRepository.save(customer).block();
//        //Then получаем сохраненного клиента
//        assertThat(saved).isNotNull();
//        assertThat(saved.id()).isPresent();
//        final Customer byId = customerRepository.findById(saved.id().get()).block();
//        assertThat(saved.name()).isEqualTo(customer.name()).isEqualTo(byId.name());
//        customerRepository.deleteById(saved.id().get()).block();
//    }
//
//}