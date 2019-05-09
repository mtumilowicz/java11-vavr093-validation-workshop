package com.example.vavr.validation.workshop.rest.person;

import com.example.vavr.validation.workshop.person.PersonRequestPatchService;
import com.example.vavr.validation.workshop.person.PersonService;
import com.example.vavr.validation.workshop.rest.ErrorMessages;
import com.example.vavr.validation.workshop.rest.person.request.PersonRequestValidation;
import com.example.vavr.validation.workshop.rest.person.request.PersonSaveRequest;
import com.example.vavr.validation.workshop.rest.person.response.PersonSaveResponse;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
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
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class PersonController {

    PersonService personService = new PersonService();
    PersonRequestPatchService patchService = new PersonRequestPatchService();
    
    @PostMapping("/save")
    public Either<ResponseEntity<ErrorMessages>, ResponseEntity<PersonSaveResponse>> save(@RequestBody PersonSaveRequest personSaveRequest) {
        return Match(PersonRequestValidation.validate(personSaveRequest)).of(
                Case($Valid($()), valid -> Either.right(ResponseEntity.ok(PersonSaveResponse.of(personService.save(valid))))),
                Case($Invalid($()), invalid -> patchService.patchSaveRequest(personSaveRequest)
                        .map(personService::save)
                        .map(PersonSaveResponse::of)
                        .toEither(ErrorMessages.of(invalid))
                        .mapLeft(error -> ResponseEntity.badRequest().body(error))
                        .map(ResponseEntity::ok))
        );
    }
}
