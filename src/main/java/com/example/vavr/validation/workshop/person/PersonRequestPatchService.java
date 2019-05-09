package com.example.vavr.validation.workshop.person;

import com.example.vavr.validation.workshop.rest.person.request.PersonSaveRequest;
import com.example.vavr.validation.workshop.rest.person.request.ValidPersonSaveRequest;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PersonRequestPatchService {
    public Option<ValidPersonSaveRequest> patchSaveRequest(PersonSaveRequest request) {
        return Option.none();
    }
}
