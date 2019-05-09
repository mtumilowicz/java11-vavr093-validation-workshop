package com.example.vavr.validation.workshop.address;

import com.example.vavr.validation.workshop.patterns.PostalCode;
import com.example.vavr.validation.workshop.patterns.Word;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
public class AddressRequestValidation {
    public static Validation<Seq<String>, ValidAddressRequest> validate(AddressRequest request) {

        return Validation
                .combine(
                        Word.validate(request.getCity()),
                        PostalCode.validate(request.getPostalCode()))
                .ap((city, postalCode) -> ValidAddressRequest.builder()
                        .city(Word.of(city))
                        .postalCode(PostalCode.of(postalCode))
                        .build());
    }
}
