package com.example.vavr.validation.workshop.person.patterns


import io.vavr.control.Validation
import spock.lang.Specification 
/**
 * Created by mtumilowicz on 2019-05-13.
 */
class NameAnswerTest extends Specification {

    def "validate - valid"() {
        expect:
        Name.validateAnswer('Alfred') == Validation.valid(Name.of('Alfred'))
    }

    def "validate - invalid"() {
        expect:
        Name.validateAnswer('%') == Validation.invalid('Name: % is not valid!')
    }
    
}
