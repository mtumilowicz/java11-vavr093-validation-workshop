package com.example.vavr.validation.workshop.person;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
public class PersonRepository {

    public Person save(Person person) {
        return person.withId(PersonId.of(1));
    }
}
