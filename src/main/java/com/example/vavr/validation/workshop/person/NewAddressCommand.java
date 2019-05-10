package com.example.vavr.validation.workshop.person;

import com.example.vavr.validation.workshop.person.patterns.PostalCode;
import com.example.vavr.validation.workshop.person.patterns.Word;
import lombok.Builder;
import lombok.Value;

/**
 * Created by mtumilowicz on 2019-05-10.
 */
@Value
@Builder
public
class NewAddressCommand {
    PostalCode postalCode;
    Word city;
}
