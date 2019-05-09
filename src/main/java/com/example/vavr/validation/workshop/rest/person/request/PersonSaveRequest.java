package com.example.vavr.validation.workshop.rest.person.request;

import lombok.Data;

import java.util.List;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
@Data
public
class PersonSaveRequest {
    String name;
    AddressRequest address;
    List<String> emails;
    int age;
}

