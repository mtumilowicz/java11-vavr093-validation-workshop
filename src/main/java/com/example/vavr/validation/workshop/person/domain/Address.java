package com.example.vavr.validation.workshop.person.domain;

import com.example.vavr.validation.workshop.person.patterns.PostalCode;
import com.example.vavr.validation.workshop.person.patterns.Word;
import lombok.Builder;
import lombok.Value;

/**
 * Created by mtumilowicz on 2019-05-10.
 */
@Value
@Builder
class Address {
    PostalCode postalCode;
    Word city;
}