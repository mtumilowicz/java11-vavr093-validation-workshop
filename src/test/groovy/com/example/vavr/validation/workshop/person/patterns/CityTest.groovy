package com.example.vavr.validation.workshop.person.patterns

import com.example.vavr.validation.workshop.intrastructure.ValidationException
import io.vavr.collection.List
import io.vavr.control.Validation
import spock.lang.Specification

/**
 * Created by mtumilowicz on 2019-05-13.
 */
class CityTest extends Specification {
    
    def "validate - valid"() {
        expect:
        City.validateAnswer('Warsaw') == Validation.valid(City.of('Warsaw'))
    }

    def "validate - invalid"() {
        expect:
        City.validateAnswer('%') == Validation.invalid('City: % is not valid!')
    }

    def "validateWorkshop - valid"() {
        expect:
        City.validateWorkshop('Warsaw') == City.of('Warsaw')
    }

    def "validateWorkshop - invalid"() {
        when:
        City.validateWorkshop('%')

        then:
        ValidationException ex = thrown()
        ex.getErrors() == List.of('City: % is not valid!')
    }
}
