package com.example.vavr.validation.workshop.person;

import com.example.vavr.validation.workshop.patterns.Age;
import com.example.vavr.validation.workshop.patterns.Emails;
import com.example.vavr.validation.workshop.patterns.Word;
import com.example.vavr.validation.workshop.rest.person.request.ValidAddressRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Value
@Builder
@Wither
public class Person {
    PersonId id;
    Word name;
    ValidAddressRequest address;
    Emails emails;
    Age age;
}
