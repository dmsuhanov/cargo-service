package com.suhan.cargo.domain;

import com.suhan.cargo.domain.cargo.Cargo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CargoFactory {

    public Cargo createNew() {
        return null;
//        return new Cargo(new DeliveryHistory(new ArrayList<>()));
    }

}
