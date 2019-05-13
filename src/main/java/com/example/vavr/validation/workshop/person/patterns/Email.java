package com.example.vavr.validation.workshop.person.patterns;


import com.example.vavr.validation.workshop.intrastructure.NewPersonRequestValidationException;
import com.google.common.base.Preconditions;
import io.vavr.collection.List;
import io.vavr.control.Validation;
import lombok.NonNull;
import lombok.Value;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
@Value
public class Email {
    public static final Predicate<String> VALIDATOR = Pattern.compile("[\\w._%+-]+@[\\w.-]+\\.[\\w]{2,}")
            .asMatchPredicate();

    private static final Function<String, String> errorMessage = email -> "Email: " + email + " is not valid!";

    String email;

    private Email(String email) {
        this.email = email;
    }

    public static Email of(@NonNull String email) {
        Preconditions.checkArgument(VALIDATOR.test(email));

        return new Email(email);
    }

    public static Validation<List<String>, Emails> validate(List<String> emails) {
        return emails.partition(VALIDATOR)
                .apply((successes, failures) -> failures.isEmpty()
                        ? Validation.valid(successes.map(Email::new).transform(Emails::new))
                        : Validation.invalid(failures.map(errorMessage)));
    }

    public static Emails validateWorkshop(List<String> emails) {
        var validEmailsMap = emails.collect(Collectors.partitioningBy(VALIDATOR));
        if (validEmailsMap.get(true).isEmpty()) {
            throw NewPersonRequestValidationException.of(List.ofAll(emails.map(errorMessage)));
        }

        return new Emails(emails.map(Email::new));
    }
}