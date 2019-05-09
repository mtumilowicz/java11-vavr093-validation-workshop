package com.example.vavr.validation.workshop.person;

import com.example.vavr.validation.workshop.intrastructure.ModelMapperConfig;
import com.example.vavr.validation.workshop.rest.person.request.ValidPersonRequest;
import org.modelmapper.ModelMapper;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
public class PersonService {
    PersonRepository personRepository = new PersonRepository();
    ModelMapper mapper = ModelMapperConfig.directFieldMapper();

    public Person save(ValidPersonRequest validPersonRequest) {
        return personRepository.save(map(validPersonRequest));
    }

    public Person map(ValidPersonRequest validPersonRequest) {
        return mapper.map(validPersonRequest, Person.PersonBuilder.class).build();
    }
}
