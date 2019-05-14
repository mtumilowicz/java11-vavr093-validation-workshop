package com.example.vavr.validation.workshop.person.patterns

import com.example.vavr.validation.workshop.intrastructure.ValidationException
import io.vavr.collection.List
import io.vavr.control.Validation
import spock.lang.Specification

/**
 * Created by mtumilowicz on 2019-05-13.
 */
class NameTest extends Specification {

    def "validate - valid"() {
        expect:
        Name.validate('Alfred') == Validation.valid(Name.of('Alfred'))
    }

    def "validate - invalid"() {
        expect:
        Name.validate('%') == Validation.invalid('Name: % is not valid!')
    }

    def "validateWorkshop - valid"() {
        expect:
        Name.validateWorkshop('Alfred') == Name.of('Alfred')
    }

    def "validateWorkshop - invalid"() {
        when:
        Name.validateWorkshop('%')

        then:
        ValidationException ex = thrown()
        ex.getErrors() == List.of('Name: % is not valid!')
    }
    
}
