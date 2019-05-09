package com.example.vavr.validation.workshop.rest;

import com.example.vavr.validation.workshop.person.Person;
import com.example.vavr.validation.workshop.person.PersonService;
import com.example.vavr.validation.workshop.rest.person.request.PersonRequestValidation;
import com.example.vavr.validation.workshop.rest.person.request.PersonSaveRequest;
import com.example.vavr.validation.workshop.rest.person.response.PersonSaveResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static io.vavr.API.*;
import static io.vavr.Patterns.$Invalid;
import static io.vavr.Patterns.$Valid;

/**
 * Created by mtumilowicz on 2019-05-08.
 */
@RestController
public class PersonController {

    PersonService personService = new PersonService();

    @PostMapping("/")
    /**
     * {
     *   "address": {
     *     "city": "Warsaw",
     *     "postalCode": "00-001"
     *   },
     *   "age": 28
     *   "emails": [
     *     "AlfredHiczkok@hollywood.com"
     *   ],
     *   "name": "Alfredzik"
     * }
     */
    public ResponseEntity save(@RequestBody PersonSaveRequest personSaveRequest) {
        return Match(PersonRequestValidation.validate(personSaveRequest))
                .of(
                        Case($Valid($()), valid -> ResponseEntity.ok(map(personService.save(valid)))),
                        Case($Invalid($()), invalid -> ResponseEntity.badRequest().body(ErrorMessages.of(invalid)))
                );
    }

    PersonSaveResponse map(Person person) {
        return PersonSaveResponse.builder()
                .id(person.getId().getValue())
                .build();
    }
}
