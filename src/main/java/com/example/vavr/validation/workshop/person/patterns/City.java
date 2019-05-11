package com.example.vavr.validation.workshop.person.patterns;

import com.google.common.base.Preconditions;
import io.vavr.control.Validation;
import lombok.NonNull;
import lombok.Value;

import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Created by mtumilowicz on 2019-05-11.
 */
@Value
public class City {
    public static final Predicate<String> VALIDATOR = Pattern.compile("[\\w]+").asPredicate();

    String city;

    private City(String city) {
        this.city = city;
    }

    public static City of(@NonNull String city) {
        Preconditions.checkArgument(VALIDATOR.test(city));

        return new City(city);
    }

    public static Validation<String, City> validate(String city) {
        return VALIDATOR.test(city)
                ? Validation.valid(new City(city))
                : Validation.invalid(city + " is not a proper city!");
    }
}
