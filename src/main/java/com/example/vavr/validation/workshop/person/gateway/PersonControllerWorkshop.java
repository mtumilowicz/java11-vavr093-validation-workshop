package com.example.vavr.validation.workshop.person.gateway;

import com.example.vavr.validation.workshop.intrastructure.ErrorMessages;
import com.example.vavr.validation.workshop.person.domain.NewPersonCommand;
import com.example.vavr.validation.workshop.person.domain.PersonRequestPatchService;
import com.example.vavr.validation.workshop.person.domain.PersonService;
import com.example.vavr.validation.workshop.person.gateway.input.NewPersonRequest;
import com.example.vavr.validation.workshop.person.gateway.input.NewPersonRequestValidator;
import com.example.vavr.validation.workshop.person.gateway.output.NewPersonResponse;
import io.vavr.collection.Seq;
import io.vavr.control.Either;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mtumilowicz on 2019-05-08.
 */
@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
class PersonControllerWorkshop {

    PersonService personService;
    PersonRequestPatchService patchService;

    /**
     * rewrite using pattern matching
     * <p>
     * hints - useful methods:
     * Match(validation).of
     * Case($Valid($()), ...)
     * Case($Invalid($()), ...)
     */
    @PostMapping("workshop/person/new")
    public ResponseEntity<Either<ErrorMessages, NewPersonResponse>> newPerson(
            @RequestBody NewPersonRequest newPersonRequest) {
        var validation = NewPersonRequestValidator.validate(newPersonRequest);
        return validation.isValid()
                ? newPersonCommand(validation.get())
                : patchNewPersonCommand(newPersonRequest, validation.getError());
    }

    private ResponseEntity<Either<ErrorMessages, NewPersonResponse>> newPersonCommand(
            NewPersonCommand command) {
        return ResponseEntity.ok(Either.right(NewPersonResponse.of(personService.save(command))));
    }

    /**
     * rewrite using pattern matching
     *
     * hints - useful methods:
     * Match(validation).of
     * Case($Some($()), ...)
     * Case($None(), ...)
     */
    private ResponseEntity<Either<ErrorMessages, NewPersonResponse>> patchNewPersonCommand(
            NewPersonRequest newPersonRequest,
            Seq<String> errors) {

        return patchService.patchSaveRequest(newPersonRequest)
                .map(this::newPersonCommand)
                .getOrElse(() -> ResponseEntity.badRequest().body(Either.left(ErrorMessages.of(errors))));
    }
}
