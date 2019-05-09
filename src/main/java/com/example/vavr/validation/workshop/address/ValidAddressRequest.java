package com.example.vavr.validation.workshop.address;

import com.example.vavr.validation.workshop.patterns.PostalCode;
import com.example.vavr.validation.workshop.patterns.Word;
import lombok.Builder;
import lombok.Value;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
@Value
@Builder
public class ValidAddressRequest {
    PostalCode postalCode;
    Word city;
}
