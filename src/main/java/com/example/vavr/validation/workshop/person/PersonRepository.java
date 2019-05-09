package com.example.vavr.validation.workshop.person;

import com.example.vavr.validation.workshop.person.patterns.PersonId;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
class PersonRepository {

    Person save(Person person) {
        return person.withId(PersonId.of(1));
    }
}
