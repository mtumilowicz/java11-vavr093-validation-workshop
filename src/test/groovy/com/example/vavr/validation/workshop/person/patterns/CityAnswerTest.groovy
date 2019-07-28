package com.example.vavr.validation.workshop.person.patterns


import io.vavr.control.Validation
import spock.lang.Specification 
/**
 * Created by mtumilowicz on 2019-05-13.
 */
class CityAnswerTest extends Specification {
    
    def "validate - valid"() {
        expect:
        City.validateAnswer('Warsaw') == Validation.valid(City.of('Warsaw'))
    }

    def "validate - invalid"() {
        expect:
        City.validateAnswer('%') == Validation.invalid('City: % is not valid!')
    }
    
}
