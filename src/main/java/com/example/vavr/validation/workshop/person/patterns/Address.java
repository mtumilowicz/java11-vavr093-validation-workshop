package com.example.vavr.validation.workshop.person.patterns;

import lombok.Builder;
import lombok.Value;

/**
 * Created by mtumilowicz on 2019-05-10.
 */
@Value
@Builder
public class Address {
    PostalCode postalCode;
    Word city;
}
