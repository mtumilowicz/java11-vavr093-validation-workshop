package com.example.vavr.validation.workshop;

import com.example.vavr.validation.workshop.person.PersonRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mtumilowicz on 2019-05-08.
 */
@RestController
public class GreetingController {

    @PostMapping("/")
    public ResponseEntity<PersonRequest> greeting(@RequestBody PersonRequest personRequest) {
        return ResponseEntity.ok(personRequest);
    }
}
