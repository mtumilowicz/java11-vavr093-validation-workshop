package com.example.vavr.validation.workshop.person.patterns;

import com.example.vavr.validation.workshop.intrastructure.NewPersonRequestValidationException;
import com.google.common.base.Preconditions;
import io.vavr.collection.List;
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
    public static final Predicate<String> VALIDATOR = Pattern.compile("[\\w]+").asMatchPredicate();

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
                : Validation.invalid("City: " + city + " is not valid!");
    }

    public static City validateWorkshop(String city) {
        if (!VALIDATOR.test(city)) {
            throw NewPersonRequestValidationException.of(List.of("City: " + city + " is not valid!"));
        }
        
        return new City(city);
    }
}
