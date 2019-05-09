package com.example.vavr.validation.workshop.person;

import com.example.vavr.validation.workshop.intrastructure.ModelMapperConfig;
import com.example.vavr.validation.workshop.rest.person.request.ValidPersonRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PersonService {
    PersonRepository personRepository = new PersonRepository();
    ModelMapper mapper = ModelMapperConfig.directFieldMapper();

    public Person save(ValidPersonRequest validPersonRequest) {
        return personRepository.save(mapToPerson(validPersonRequest));
    }

    private Person mapToPerson(ValidPersonRequest validPersonRequest) {
        return mapper.map(validPersonRequest, Person.PersonBuilder.class).build();
    }
}
