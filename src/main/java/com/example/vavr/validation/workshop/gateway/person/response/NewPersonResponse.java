package com.example.vavr.validation.workshop.gateway.person.response;

import com.example.vavr.validation.workshop.person.patterns.PersonId;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
@Getter
@Builder
public class NewPersonResponse {
    int id;

    public static NewPersonResponse of(PersonId id) {
        return NewPersonResponse.builder()
                .id(id.getValue())
                .build();
    }
}
