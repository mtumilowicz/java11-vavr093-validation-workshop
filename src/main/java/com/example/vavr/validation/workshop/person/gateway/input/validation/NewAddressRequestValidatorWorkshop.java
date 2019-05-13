package com.example.vavr.validation.workshop.person.gateway.input.validation;

import com.example.vavr.validation.workshop.intrastructure.NewPersonRequestValidationException;
import com.example.vavr.validation.workshop.person.domain.NewAddressCommand;
import com.example.vavr.validation.workshop.person.gateway.input.NewAddressRequest;
import com.example.vavr.validation.workshop.person.patterns.City;
import com.example.vavr.validation.workshop.person.patterns.PostalCode;
import io.vavr.collection.List;

import java.util.LinkedList;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
class NewAddressRequestValidatorWorkshop {
    static NewAddressCommand validate(NewAddressRequest request) {

        var errors = new LinkedList<String>();
        City city = null;
        PostalCode postalCode = null;

        try {
            city = City.validateWorkshop(request.getCity());
        } catch (NewPersonRequestValidationException ex) {
            errors.addAll(ex.getErrors().asJava());
        }

        try {
            postalCode = PostalCode.validateWorkshop(request.getPostalCode());
        } catch (NewPersonRequestValidationException ex) {
            errors.addAll(ex.getErrors().asJava());
        }

        if (!errors.isEmpty()) {
            throw NewPersonRequestValidationException.of(List.ofAll(errors));
        }

        return NewAddressCommand.builder()
                .city(city)
                .postalCode(postalCode)
                .build();
    }
}
