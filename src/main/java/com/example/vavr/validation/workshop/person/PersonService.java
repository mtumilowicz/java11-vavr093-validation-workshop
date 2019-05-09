package com.example.vavr.validation.workshop.person;

import com.example.vavr.validation.workshop.intrastructure.ModelMapperConfig;
import com.example.vavr.validation.workshop.rest.person.request.ValidPersonSaveRequest;
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

    public Person save(ValidPersonSaveRequest validPersonSaveRequest) {
        return personRepository.save(mapToPerson(validPersonSaveRequest));
    }

    private Person mapToPerson(ValidPersonSaveRequest validPersonSaveRequest) {
        return mapper.map(validPersonSaveRequest, Person.PersonBuilder.class).build();
    }
}
