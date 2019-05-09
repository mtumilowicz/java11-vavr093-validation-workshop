package com.example.vavr.validation.workshop.rest.person.request;

import com.example.vavr.validation.workshop.person.patterns.Age;
import com.example.vavr.validation.workshop.person.patterns.Email;
import com.example.vavr.validation.workshop.person.patterns.Emails;
import com.example.vavr.validation.workshop.person.patterns.Word;
import com.example.vavr.validation.workshop.validation.NumberValidation;
import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
public class PersonRequestValidation {
    public static Validation<Seq<String>, ValidPersonSaveRequest> validate(PersonSaveRequest request) {

        return Validation
                .combine(
                        Word.validate(request.getName()),
                        Email.validate(List.ofAll(request.getEmails())).mapError(error -> error.mkString(", ")),
                        AddressRequestValidation.validate(request.getAddress()).mapError(error -> error.mkString(", ")),
                        NumberValidation.positive(request.getAge()))
                .ap((name, emails, address, age) -> ValidPersonSaveRequest.builder()
                        .name(Word.of(name))
                        .emails(emails.map(Email::of).transform(Emails::new))
                        .address(address)
                        .age(Age.of(age))
                        .build());
    }
}
