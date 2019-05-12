package com.example.vavr.validation.workshop.person.patterns;


import com.google.common.base.Preconditions;
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
    public static final Predicate<String> VALIDATOR = Pattern.compile("[\\w]+").asPredicate();
    
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
                : Validation.invalid(name + " is not a valid name!");
    }
}
