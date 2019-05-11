package com.example.vavr.validation.workshop.person.gateway.input;

import com.example.vavr.validation.workshop.person.domain.NewAddressCommand;
import com.example.vavr.validation.workshop.person.patterns.City;
import com.example.vavr.validation.workshop.person.patterns.PostalCode;
import com.example.vavr.validation.workshop.person.patterns.Word;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
class NewAddressRequestValidator {
    static Validation<Seq<String>, NewAddressCommand> validate(NewAddressRequest request) {

        return Validation
                .combine(
                        Word.validate(request.getCity()),
                        PostalCode.validate(request.getPostalCode()))
                .ap((city, postalCode) -> NewAddressCommand.builder()
                        .city(City.of(city))
                        .postalCode(PostalCode.of(postalCode))
                        .build());
    }
}
