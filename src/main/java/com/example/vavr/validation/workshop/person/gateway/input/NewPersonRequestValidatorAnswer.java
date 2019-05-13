package com.example.vavr.validation.workshop.person.gateway.input;

import com.example.vavr.validation.workshop.person.domain.NewPersonCommand;
import com.example.vavr.validation.workshop.person.patterns.Age;
import com.example.vavr.validation.workshop.person.patterns.Email;
import com.example.vavr.validation.workshop.person.patterns.Name;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

import java.util.function.Function;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
public class NewPersonRequestValidatorAnswer {
    private static Function<Seq<String>, String> concatByComma = strings -> strings.mkString(", ");
    
    public static Validation<Seq<String>, NewPersonCommand> validate(NewPersonRequest request) {

        return Validation
                .combine(
                        Name.validate(request.getName()),
                        Email.validate(request.getEmails()).mapError(concatByComma),
                        NewAddressRequestAnswerValidator.validate(request.getAddress()).mapError(concatByComma),
                        Age.validate(request.getAge()))
                .ap((name, emails, address, age) -> NewPersonCommand.builder()
                        .name(name)
                        .emails(emails)
                        .address(address)
                        .age(age)
                        .build());
    }
}
