package com.example.vavr.validation.workshop.rest.person;

import com.example.vavr.validation.workshop.person.PersonRequestPatchService;
import com.example.vavr.validation.workshop.person.PersonService;
import com.example.vavr.validation.workshop.rest.ErrorMessages;
import com.example.vavr.validation.workshop.rest.person.request.PersonRequestValidation;
import com.example.vavr.validation.workshop.rest.person.request.NewPersonRequest;
import com.example.vavr.validation.workshop.rest.person.response.NewPersonResponse;
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
    public Either<ResponseEntity<ErrorMessages>, ResponseEntity<NewPersonResponse>> save(@RequestBody NewPersonRequest newPersonRequest) {
        return Match(PersonRequestValidation.validate(newPersonRequest)).of(
                Case($Valid($()), valid -> Either.right(ResponseEntity.ok(NewPersonResponse.of(personService.save(valid))))),
                Case($Invalid($()), invalid -> patchService.patchSaveRequest(newPersonRequest)
                        .map(personService::save)
                        .map(NewPersonResponse::of)
                        .toEither(ErrorMessages.of(invalid))
                        .mapLeft(error -> ResponseEntity.badRequest().body(error))
                        .map(ResponseEntity::ok))
        );
    }
}
