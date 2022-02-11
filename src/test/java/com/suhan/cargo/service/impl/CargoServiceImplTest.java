package com.suhan.cargo.service.impl;

import com.suhan.cargo.domain.cargo.CargoDao;
import com.suhan.cargo.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CargoServiceImplTest {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private CargoDao cargoDao;

//    @Test
//    public void shouldCreateNewCargo() {
//        //Given empty
//        //When вызываем метод создания нового груза
//        Cargo cargo = cargoService.createNew().block();
//        //Then получаем созданный новый груз
//        assertThat(cargo).isNotNull()
//                .matches(a -> a.id().isPresent())
//                .matches(a -> a.createAt() != null)
//                .matches(a -> a.deliveryHistory().equals(new DeliveryHistory(new ArrayList<>())))
//                .matches(a -> a.goal().isEmpty())
//                .matches(a -> a.customerRoles().isEmpty())
//        ;
//        cargoDao.deleteById(cargo.id().orElseThrow()).block();
//    }

}