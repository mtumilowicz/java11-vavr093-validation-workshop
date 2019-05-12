package com.example.vavr.validation.workshop.person.patterns;

import com.google.common.base.Preconditions;
import io.vavr.control.Validation;
import lombok.Value;

import java.util.function.IntPredicate;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
@Value
public class Age {
    public static final IntPredicate VALIDATOR = i -> i > 0;
    
    int age;

    private Age(int age) {
        this.age = age;
    }
    
    public static Age of(int age) {
        Preconditions.checkArgument(VALIDATOR.test(age));
        
        return new Age(age);
    }

    public static Validation<String, Age> validate(int age) {
        return VALIDATOR.test(age)
                ? Validation.valid(new Age(age))
                : Validation.invalid("Age: " + age + " is not > 0");
    }
}
