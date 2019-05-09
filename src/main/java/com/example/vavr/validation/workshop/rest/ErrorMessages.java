package com.example.vavr.validation.workshop.rest;

import lombok.Value;

import java.util.LinkedList;
import java.util.List;

import static io.vavr.API.*;
import static io.vavr.Predicates.isNull;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
@Value
public class ErrorMessages {
    List<String> messages;

    public static ErrorMessages of(io.vavr.collection.Seq<String> messages) {
        return Match(messages).of(
                Case($(isNull()), () -> new ErrorMessages(new LinkedList<>())),
                Case($(), () -> new ErrorMessages(messages.asJava()))
        );
    }
}
