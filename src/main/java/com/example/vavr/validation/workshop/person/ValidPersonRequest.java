package com.example.vavr.validation.workshop.person;

import com.example.vavr.validation.workshop.address.ValidAddressRequest;
import com.example.vavr.validation.workshop.patterns.Age;
import com.example.vavr.validation.workshop.patterns.Emails;
import com.example.vavr.validation.workshop.patterns.Word;
import lombok.Builder;
import lombok.Value;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
@Builder
@Value
public class ValidPersonRequest {
    Word name;
    ValidAddressRequest address;
    Emails emails;
    Age age;
}
