package com.suhan.cargo.domain.cargo;

import com.suhan.cargo.domain.customer.Customer;
import com.suhan.cargo.domain.customer.CustomerRepository;
import com.suhan.cargo.domain.role.Role;
import com.suhan.cargo.domain.role.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.relational.core.query.Criteria.where;
import static org.springframework.data.relational.core.query.Query.query;

@SpringBootTest
class CargoRepositoryImplTest {

    @Autowired
    private R2dbcEntityTemplate template;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void shouldSaveNewCargo() {
        //Given создаем груз
//        final Cargo cargo = new Cargo(new DeliveryHistory(new ArrayList<>()));
        final Cargo cargo = new Cargo();
        //When вызываем метод сохранения груза
        Cargo saved = cargoRepository.save(cargo).block();
        //Then получаем сохраненный груз
        assertThat(saved).isNotNull();
        assertThat(saved.id()).isPresent();
        CargoEntity byId = cargoDao.findById(saved.id().get()).block();
        assertThat(byId).isNotNull();
        assertThat(byId.getCreateAt()).isNotNull();
//        assertThat(saved.customerRoles()).isNotNull().isEmpty();
//        assertThat(saved.goal()).isEmpty();
//        assertThat(saved.deliveryHistory()).isNotNull();
        cargoDao.deleteById(Mono.just(saved.id().orElseThrow())).block();
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void shouldUpdateNewCargo() {
        //Given новый созданный груз, отправитель, получатель
        Cargo cargo = cargoRepository.save(new Cargo()).block();
        Customer sender = customerRepository.save(new Customer("name_sender")).block();
        Role roleSender = roleRepository.sender().block();
        cargo = cargo.addCustomerWithRole(sender, roleSender);
//        Customer recipient = customerRepository.save(new Customer("recipient")).block();
//        cargo = cargo.addRecipient(recipient);
        //When вызываем метод сохранения груза
        cargo = cargoRepository.save(cargo).block();
        //Then получаем обновленный груз
        assertThat(cargo).isNotNull();
        assertThat(cargo.unitCustomerRoles().object().size()).isEqualTo(1);
        Cargo byId = cargoRepository.findById(cargo.id().get()).block();
        assertThat(byId.unitCustomerRoles().object().size()).isEqualTo(1);
        assertThat(byId.unitCustomerRoles().object()).isEqualTo(cargo.unitCustomerRoles().object());
        Customer recipient = customerRepository.save(new Customer("name_recipient")).block();
        Role roleRecipient = roleRepository.recipient().block();
        cargo = cargo.addCustomerWithRole(recipient, roleRecipient);
        cargo = cargoRepository.save(cargo).block();
        assertThat(cargo.unitCustomerRoles().object().size()).isEqualTo(2);
        Integer resultDelete = template.delete(CargoCustomerRoleEntity.class)
                .matching(query(where("cargo_id").is(cargo.id().get())))
                .all().block();
        cargoRepository.deleteById(cargo.id().orElseThrow()).block();
    }

}