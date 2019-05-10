package com.example.vavr.validation.workshop.person.gateway.input;

import lombok.Data;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
@Data
class NewAddressRequest {
    String postalCode;
    String city;
}
