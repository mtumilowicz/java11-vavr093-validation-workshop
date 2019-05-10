package com.example.vavr.validation.workshop.person.gateway;

import com.example.vavr.validation.workshop.intrastructure.ErrorMessages;
import com.example.vavr.validation.workshop.person.NewPersonCommand;
import com.example.vavr.validation.workshop.person.PersonRequestPatchService;
import com.example.vavr.validation.workshop.person.PersonService;
import com.example.vavr.validation.workshop.person.gateway.input.NewPersonRequest;
import com.example.vavr.validation.workshop.person.gateway.input.NewPersonRequestValidator;
import com.example.vavr.validation.workshop.person.gateway.output.NewPersonResponse;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static io.vavr.API.*;
import static io.vavr.Patterns.*;

/**
 * Created by mtumilowicz on 2019-05-08.
 */
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
class PersonController {

    PersonService personService = new PersonService();
    PersonRequestPatchService patchService = new PersonRequestPatchService();

    @PostMapping("/person/new")
    public Either<ResponseEntity<ErrorMessages>, ResponseEntity<NewPersonResponse>> newPerson(
            @RequestBody NewPersonRequest newPersonRequest) {
        return Match(NewPersonRequestValidator.validate(newPersonRequest)).of(
                Case($Valid($()), this::newPersonCommand),
                Case($Invalid($()), errors -> patchNewPersonCommand(newPersonRequest, errors))
        );
    }

    private Either<ResponseEntity<ErrorMessages>, ResponseEntity<NewPersonResponse>> newPersonCommand(
            NewPersonCommand command) {
        return Either.right(ResponseEntity.ok(NewPersonResponse.of(personService.save(command))));
    }

    private Either<ResponseEntity<ErrorMessages>, ResponseEntity<NewPersonResponse>> patchNewPersonCommand(
            NewPersonRequest newPersonRequest,
            Seq<String> errors) {
        return Match(patchService.patchSaveRequest(newPersonRequest)).of(
                Case($Some($()), this::newPersonCommand),
                Case($None(), () -> Either.left(ResponseEntity.badRequest().body(ErrorMessages.of(errors))))
        );
    }
}
