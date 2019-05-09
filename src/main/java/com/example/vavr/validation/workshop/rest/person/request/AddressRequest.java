package com.example.vavr.validation.workshop.rest.person.request;

import lombok.Data;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
@Data
public class AddressRequest {
    String postalCode;
    String city;
}
