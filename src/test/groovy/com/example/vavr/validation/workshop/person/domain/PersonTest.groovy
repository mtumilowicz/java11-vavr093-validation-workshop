package com.example.vavr.validation.workshop.person.domain

import spock.lang.Specification

/**
 * Created by mtumilowicz on 2019-05-11.
 */
class PersonTest extends Specification {

    def "builder: cannot create person with null fields"() {
        when:
        Person.builder().build()

        then:
        thrown(NullPointerException)
    }

    def "builder: age should be non null"() {
        when:
        Person.builder().age().build()

        then:
        thrown(NullPointerException)
    }

    def "builder: address should be non null"() {
        when:
        Person.builder().address().build()

        then:
        thrown(NullPointerException)
    }

    def "builder: name should be non null"() {
        when:
        Person.builder().name().build()

        then:
        thrown(NullPointerException)
    }

    def "builder: id should be non null"() {
        when:
        Person.builder().id().build()

        then:
        thrown(NullPointerException)
    }

    def "builder: emails should be non null"() {
        when:
        Person.builder().emails().build()

        then:
        thrown(NullPointerException)
    }
}
