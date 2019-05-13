package com.example.vavr.validation.workshop.person.gateway.input;

import com.example.vavr.validation.workshop.intrastructure.NewPersonRequestValidationException;
import com.example.vavr.validation.workshop.person.domain.NewAddressCommand;
import com.example.vavr.validation.workshop.person.domain.NewPersonCommand;
import com.example.vavr.validation.workshop.person.patterns.Age;
import com.example.vavr.validation.workshop.person.patterns.Email;
import com.example.vavr.validation.workshop.person.patterns.Emails;
import com.example.vavr.validation.workshop.person.patterns.Name;
import io.vavr.collection.List;
import io.vavr.collection.Seq;

import java.util.LinkedList;
import java.util.function.Function;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
public class NewPersonRequestValidatorWorkshop {
    private static Function<Seq<String>, String> concatByComma = strings -> strings.mkString(", ");

    public static NewPersonCommand validate(NewPersonRequest request) {
        var errors = new LinkedList<String>();
        Name name = null;
        Emails emails = null;
        NewAddressCommand address = null;
        Age age = null;

        try {
            name = Name.validateWorkshop(request.getName());
        } catch (NewPersonRequestValidationException ex) {
            errors.addAll(ex.getErrors().asJava());
        }

        try {
            emails = Email.validateWorkshop(request.getEmails());
        } catch (NewPersonRequestValidationException ex) {
            errors.addAll(ex.getErrors().asJava());
        }

        try {
            address = NewAddressRequestValidatorWorkshop.validate(request.getAddress());
        } catch (NewPersonRequestValidationException ex) {
            errors.add(concatByComma.apply(ex.getErrors()));
        }

        try {
            age = Age.validateWorkshop(request.getAge());
        } catch (NewPersonRequestValidationException ex) {
            errors.addAll(ex.getErrors().asJava());
        }

        if (!errors.isEmpty()) {
            throw NewPersonRequestValidationException.of(List.ofAll(errors));
        }

        return NewPersonCommand.builder()
                .name(name)
                .emails(emails)
                .address(address)
                .age(age)
                .build();
    }
}
