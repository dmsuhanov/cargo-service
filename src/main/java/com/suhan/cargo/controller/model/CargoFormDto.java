package com.suhan.cargo.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CargoFormDto {

    private UUID customerSenderId;
    private UUID locationFromId;
    private UUID customerRecipientId;
    private UUID locationToId;

}
