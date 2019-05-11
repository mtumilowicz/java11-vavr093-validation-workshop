package com.example.vavr.validation.workshop.person.domain;

import com.example.vavr.validation.workshop.person.patterns.PersonId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import static io.vavr.API.*;
import static io.vavr.Predicates.isNotNull;
import static io.vavr.Predicates.isNull;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PersonService {
    PersonRepository personRepository;

    public PersonId save(NewPersonCommand newPersonCommand) {
        return personRepository.save(mapToPerson(newPersonCommand)).getId();
    }

    private Person mapToPerson(NewPersonCommand newPersonCommand) {
        return Person.builder()
                .name(newPersonCommand.getName())
                .age(newPersonCommand.getAge())
                .address(Match(newPersonCommand.getAddress()).of(
                        Case($(isNull()), () -> null),
                        Case($(isNotNull()), address ->
                                Address.builder()
                                        .city(address.getCity())
                                        .postalCode(address.getPostalCode())
                                        .build()
                        )
                ))
                .emails(newPersonCommand.getEmails())
                .build();
    }
}
