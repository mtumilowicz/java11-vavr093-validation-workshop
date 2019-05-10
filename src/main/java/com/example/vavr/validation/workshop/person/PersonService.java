package com.example.vavr.validation.workshop.person;

import com.example.vavr.validation.workshop.intrastructure.ModelMapperConfig;
import com.example.vavr.validation.workshop.person.patterns.PersonId;
import com.example.vavr.validation.workshop.gateway.person.request.NewPersonCommand;
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

    public PersonId save(NewPersonCommand newPersonCommand) {
        return personRepository.save(mapToPerson(newPersonCommand)).getId();
    }

    private Person mapToPerson(NewPersonCommand newPersonCommand) {
        return mapper.map(newPersonCommand, Person.PersonBuilder.class).build();
    }
}
