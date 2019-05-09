package com.example.vavr.validation.workshop.rest.person.response;

import com.example.vavr.validation.workshop.person.Person;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
@Getter
@Builder
public class PersonSaveResponse {
    int id;

    public static PersonSaveResponse of(Person person) {
        return PersonSaveResponse.builder()
                .id(person.getId().getValue())
                .build();
    }
}
