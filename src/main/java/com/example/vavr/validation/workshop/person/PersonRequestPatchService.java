package com.example.vavr.validation.workshop.person;

import com.example.vavr.validation.workshop.person.gateway.input.NewPersonRequest;
import io.vavr.control.Option;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * Created by mtumilowicz on 2019-05-09.
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PersonRequestPatchService {
    public Option<NewPersonCommand> patchSaveRequest(NewPersonRequest request) {
        return Option.none();
    }
}
