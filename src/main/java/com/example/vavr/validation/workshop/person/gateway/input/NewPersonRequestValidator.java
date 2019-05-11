package com.example.vavr.validation.workshop.person.gateway.input;

import com.example.vavr.validation.workshop.intrastructure.validator.NumberValidator;
import com.example.vavr.validation.workshop.person.domain.NewPersonCommand;
import com.example.vavr.validation.workshop.person.patterns.Age;
import com.example.vavr.validation.workshop.person.patterns.Email;
import com.example.vavr.validation.workshop.person.patterns.Emails;
import com.example.vavr.validation.workshop.person.patterns.Name;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
public class NewPersonRequestValidator {
    public static Validation<Seq<String>, NewPersonCommand> validate(NewPersonRequest request) {

        return Validation
                .combine(
                        Name.validate(request.getName()),
                        Email.validate(List.ofAll(request.getEmails())).mapError(error -> error.mkString(", ")),
                        NewAddressRequestValidator.validate(request.getAddress()).mapError(error -> error.mkString(", ")),
                        NumberValidator.positive(request.getAge()))
                .ap((name, emails, address, age) -> NewPersonCommand.builder()
                        .name(Name.of(name))
                        .emails(emails.map(Email::of).transform(Emails::new))
                        .address(address)
                        .age(Age.of(age))
                        .build());
    }
}
