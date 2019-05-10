package com.example.vavr.validation.workshop.gateway.person.input;

import com.example.vavr.validation.workshop.person.NewAddressCommand;
import com.example.vavr.validation.workshop.person.NewPersonCommand;
import com.example.vavr.validation.workshop.person.patterns.*;
import com.example.vavr.validation.workshop.validator.NumberValidator;
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
                        Word.validate(request.getName()),
                        Email.validate(List.ofAll(request.getEmails())).mapError(error -> error.mkString(", ")),
                        NewAddressRequestValidator.validate(request.getAddress()).mapError(error -> error.mkString(", ")),
                        NumberValidator.positive(request.getAge()))
                .ap((name, emails, address, age) -> NewPersonCommand.builder()
                        .name(Word.of(name))
                        .emails(emails.map(Email::of).transform(Emails::new))
                        .address(mapToNewAddressCommand(address))
                        .age(Age.of(age))
                        .build());
    }
    
    private static  NewAddressCommand mapToNewAddressCommand(Address address) {
        return NewAddressCommand.builder()
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .build();
    }
}
