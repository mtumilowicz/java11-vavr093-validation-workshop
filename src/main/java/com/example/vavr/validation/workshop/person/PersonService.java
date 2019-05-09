package com.example.vavr.validation.workshop.person;

import com.example.vavr.validation.workshop.rest.person.request.ValidPersonRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
public class PersonService {
    PersonRepository personRepository = new PersonRepository();

    public Person save(ValidPersonRequest validPersonRequest) {
        return personRepository.save(map(validPersonRequest));
    }

    public Person map(ValidPersonRequest validPersonRequest) {
        ModelMapper mm = new ModelMapper();
        mm.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        return mm.map(validPersonRequest, Person.PersonBuilder.class).build();
    }
}
