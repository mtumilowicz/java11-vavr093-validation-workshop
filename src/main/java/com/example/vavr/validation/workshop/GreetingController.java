package com.example.vavr.validation.workshop;

import com.example.vavr.validation.workshop.person.PersonRequest;
import com.example.vavr.validation.workshop.person.PersonRequestValidation;
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
public class GreetingController {

    @PostMapping("/")
    public ResponseEntity greeting(@RequestBody PersonRequest personRequest) {
        return Match(PersonRequestValidation.validate(personRequest)).of(
                Case($Valid($()), v -> ResponseEntity.ok(v)),
                Case($Invalid($()), iv -> ResponseEntity.badRequest().body(iv))
        );
    }
}
