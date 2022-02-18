package com.suhan.cargo.domain.cargo;

import com.suhan.cargo.domain.DeliverySpecification;
import com.suhan.cargo.domain.customer.Customer;
import com.suhan.cargo.domain.customer.CustomerRepository;
import com.suhan.cargo.domain.location.Location;
import com.suhan.cargo.domain.location.LocationEntity;
import com.suhan.cargo.domain.location.LocationRepository;
import com.suhan.cargo.domain.role.Role;
import com.suhan.cargo.domain.role.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.UUID;

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

    @Autowired
    private LocationRepository locationRepository;


    @SuppressWarnings("ConstantConditions")
    @Test
    public void shouldSaveNewCargo() {
        //Given новый созданный груз, отправитель, получатель
        Customer sender = customerRepository.save(new Customer("name_sender")).block();
        Role roleSender = roleRepository.sender().block();
        Customer recipient = customerRepository.save(new Customer("name_recipient")).block();
        Role roleRecipient = roleRepository.recipient().block();
        Set<CustomerRole> customerRoles = Set.of(new CustomerRole(sender, roleSender), new CustomerRole(recipient, roleRecipient));
        Location location = locationRepository.save(new Location(null, "g l name", "g l desc")).block();
        //When вызываем метод сохранения груза
        Cargo cargo = cargoRepository.create(Mono.just(new Cargo(customerRoles, new DeliverySpecification(location)))).block();
        //Then получаем сохраненный груз
        assertThat(cargo).isNotNull();
        assertThat(cargo.unitCustomerRoles().object().size()).isEqualTo(2);
        Integer resultDelete = template.delete(CargoCustomerRoleEntity.class)
                .matching(query(where("cargo_id").is(cargo.id().get())))
                .all().block();
        cargoRepository.deleteById(cargo.id().orElseThrow()).block();
        Integer resultDeleteLocation = template.delete(LocationEntity.class)
                .matching(query(where("id").is(location.id().orElseThrow())))
                .all().block();
    }

}