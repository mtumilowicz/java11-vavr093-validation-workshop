package com.example.vavr.validation.workshop.person.domain;

import io.vavr.control.Option;

import static io.vavr.API.*;
import static io.vavr.Predicates.isNotNull;

/**
 * Created by mtumilowicz on 2019-05-11.
 */
class AddressMapper {
    static Option<Address> mapFrom(NewAddressCommand command) {
        return Match(command).option(
                Case($(isNotNull()), address ->
                        Address.builder()
                                .city(address.getCity())
                                .postalCode(address.getPostalCode())
                                .build()
                )
        );
    }
}
