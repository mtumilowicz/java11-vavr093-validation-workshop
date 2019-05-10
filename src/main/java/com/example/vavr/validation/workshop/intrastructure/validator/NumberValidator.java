package com.example.vavr.validation.workshop.intrastructure.validator;

import io.vavr.control.Validation;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
public class NumberValidator {
    public static Validation<String, Integer> positive(int number) {
        return number > 0
                ? Validation.valid(number)
                : Validation.invalid(number + " is not > 0");
    }
}
