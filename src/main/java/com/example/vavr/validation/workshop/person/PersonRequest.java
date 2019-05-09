package com.example.vavr.validation.workshop.person;

import com.example.vavr.validation.workshop.address.AddressRequest;
import lombok.Data;

import java.util.List;

/**
 * Created by mtumilowicz on 2018-12-09.
 */
@Data
public class PersonRequest {
    String name;
    AddressRequest address;
    List<String> emails;
    int age;
}

