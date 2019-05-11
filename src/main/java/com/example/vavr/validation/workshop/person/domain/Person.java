package com.example.vavr.validation.workshop.person.domain;

import com.example.vavr.validation.workshop.person.patterns.*;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
@Value
@Builder
@Wither
class Person {
    PersonId id;
    Name name;
    Address address;
    Emails emails;
    Age age;
}
