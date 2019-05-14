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
public class PostalCode {
    public static final Predicate<String> VALIDATOR = Pattern.compile("\\d{2}-\\d{3}").asMatchPredicate();
    
    String raw;

    private PostalCode(String postalCode) {
        this.raw = postalCode;
    }
    
    public static PostalCode of(@NonNull String postalCode) {
        Preconditions.checkArgument(VALIDATOR.test(postalCode));
        
        return new PostalCode(postalCode);
    }

    public static Validation<String, PostalCode> validate(String postalCode) {
        return VALIDATOR.test(postalCode)
                ? Validation.valid(new PostalCode(postalCode))
                : Validation.invalid("Postal Code: " + postalCode + " is not valid!");
    }

    public static PostalCode validateWorkshop(String postalCode) {
        if (!VALIDATOR.test(postalCode)) {
            throw ValidationException.of(List.of("Postal Code: " + postalCode + " is not valid!"));
        }

        return new PostalCode(postalCode);
    }
}
