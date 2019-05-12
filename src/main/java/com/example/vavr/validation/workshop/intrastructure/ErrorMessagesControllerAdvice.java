package com.example.vavr.validation.workshop.intrastructure;

import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mtumilowicz on 2019-05-12.
 * <p>
 * class should be removed during PersonControllerWorkshop refactoring
 */
@ControllerAdvice
class ErrorMessagesControllerAdvice {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NewPersonValidationException.class)
    @ResponseBody
    ErrorMessages entityNotFoundException(@NonNull HttpServletRequest request,
                                          @NonNull NewPersonValidationException ex) {

        return ErrorMessages.of(ex.getErrors());
    }
}
