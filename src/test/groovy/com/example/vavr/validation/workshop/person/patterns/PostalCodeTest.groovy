package com.example.vavr.validation.workshop.person.patterns

import com.example.vavr.validation.workshop.intrastructure.NewPersonRequestValidationException
import io.vavr.collection.List
import io.vavr.control.Validation
import spock.lang.Specification

/**
 * Created by mtumilowicz on 2019-05-14.
 */
class PostalCodeTest extends Specification {

    def "validate - valid"() {
        expect:
        PostalCode.validate('00-001') == Validation.valid(PostalCode.of('00-001'))
    }

    def "validate - invalid"() {
        expect:
        PostalCode.validate('%') == Validation.invalid('Postal Code: % is not valid!')
    }

    def "validateWorkshop - valid"() {
        expect:
        PostalCode.validateWorkshop('00-001') == PostalCode.of('00-001')
    }

    def "validateWorkshop - invalid"() {
        when:
        PostalCode.validateWorkshop('%')

        then:
        NewPersonRequestValidationException ex = thrown()
        ex.getErrors() == List.of('Postal Code: % is not valid!')
    }
    
}
