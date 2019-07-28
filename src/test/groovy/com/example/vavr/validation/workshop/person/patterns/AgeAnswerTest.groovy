package com.example.vavr.validation.workshop.person.patterns


import io.vavr.control.Validation
import spock.lang.Specification 
/**
 * Created by mtumilowicz on 2019-05-13.
 */
class AgeAnswerTest extends Specification {
    
    def "validate - valid"() {
        expect:
        Age.validateAnswer(15) == Validation.valid(Age.of(15))
    }

    def "validate - invalid"() {
        expect:
        Age.validateAnswer(-5) == Validation.invalid('Age: -5 is not > 0')
    }
    
}
