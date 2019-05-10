package com.example.vavr.validation.workshop.rest.person.request;

import com.example.vavr.validation.workshop.person.patterns.PostalCode;
import com.example.vavr.validation.workshop.person.patterns.Word;
import lombok.Builder;
import lombok.Value;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
@Value
@Builder
public class NewAddressCommand {
    PostalCode postalCode;
    Word city;
}
