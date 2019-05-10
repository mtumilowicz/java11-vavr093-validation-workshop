package com.example.vavr.validation.workshop.person.domain;

import com.example.vavr.validation.workshop.person.patterns.PersonId;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PersonService {
    PersonRepository personRepository = new PersonRepository();

    public PersonId save(NewPersonCommand newPersonCommand) {
        return personRepository.save(mapToPerson(newPersonCommand)).getId();
    }

    private Person mapToPerson(NewPersonCommand newPersonCommand) {
        return Person.builder()
                .name(newPersonCommand.getName())
                .age(newPersonCommand.getAge())
                .address(Address.builder()
                        .city(newPersonCommand.getAddress().getCity())
                        .postalCode(newPersonCommand.getAddress().getPostalCode())
                        .build())
                .emails(newPersonCommand.getEmails())
                .build();
    }
}
