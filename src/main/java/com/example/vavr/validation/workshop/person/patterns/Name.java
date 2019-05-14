package com.example.vavr.validation.workshop.person.patterns;


import com.example.vavr.validation.workshop.intrastructure.ValidationException;
import com.google.common.base.Preconditions;
import io.vavr.collection.List;
import io.vavr.control.Validation;
import lombok.NonNull;
import lombok.Value;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
@Value
public class Name {
    public static final Predicate<String> VALIDATOR = Pattern.compile("[\\w]+").asMatchPredicate();

    String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(@NonNull String name) {
        Preconditions.checkArgument(VALIDATOR.test(name));

        return new Name(name);
    }

    public static Validation<String, Name> validate(String name) {
        return VALIDATOR.test(name)
                ? Validation.valid(new Name(name))
                : Validation.invalid("Name: " + name + " is not valid!");
    }

    public static Name validateWorkshop(String name) {
        if (!VALIDATOR.test(name)) {
            throw ValidationException.of(List.of("Name: " + name + " is not valid!"));

        }
        return new Name(name);
    }
}
