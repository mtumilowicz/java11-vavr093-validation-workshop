package com.example.vavr.validation.workshop.person;

import com.example.vavr.validation.workshop.person.patterns.Age;
import com.example.vavr.validation.workshop.person.patterns.Emails;
import com.example.vavr.validation.workshop.person.patterns.PersonId;
import com.example.vavr.validation.workshop.person.patterns.Word;
import com.example.vavr.validation.workshop.rest.person.request.ValidAddressRequest;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
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
