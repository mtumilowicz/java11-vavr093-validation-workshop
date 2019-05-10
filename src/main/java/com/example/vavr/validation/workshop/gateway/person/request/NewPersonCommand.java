package com.example.vavr.validation.workshop.gateway.person.request;

import com.example.vavr.validation.workshop.person.patterns.Address;
import com.example.vavr.validation.workshop.person.patterns.Age;
import com.example.vavr.validation.workshop.person.patterns.Emails;
import com.example.vavr.validation.workshop.person.patterns.Word;
import lombok.Builder;
import lombok.Value;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
@Builder
@Value
public class NewPersonCommand {
    Word name;
    Address address;
    Emails emails;
    Age age;
}
