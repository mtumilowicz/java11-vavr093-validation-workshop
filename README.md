[![Build Status](https://travis-ci.com/mtumilowicz/java11-vavr093-validation-workshop.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-vavr093-validation-workshop)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
# java11-vavr093-validation-workshop

# project description
* https://softwaremill.com/javaslang-data-validation/  
* https://www.vavr.io/vavr-docs/#_validation  
* https://www.baeldung.com/vavr-validation-api
* https://github.com/mtumilowicz/java11-vavr-validation
* https://github.com/mtumilowicz/java11-jsr303-custom-validation
* on the workshop we will try to rewrite all methods from classes `*Workshop` 
using hints given in the classes and refactoring plan depicted below
* refactoring plan:
    1. rewrite `patterns` using `Validation` instead of throwing exception
        * method: `validateWorkshop` in classes
            * `Age`
            * `City`
            * `Email`
            * `Name`
            * `PostalCode`
        * fix tests
            * remove tests of `validateWorkshop`
            * implement new tests - tests for `validate` could be helpful
    1. rewrite `NewAddressRequestValidatorWorkshop` using `Validation` instead 
        of throwing exception and try-catch blocks (more hints in the class)
        * fix tests 
            * rewrite `NewAddressRequestValidatorWorkshopTest` - note that 
            `NewAddressRequestValidatorAnswerTest` could be helpful
    1. rewrite `NewPersonRequestValidatorWorkshop` using `Validation` instead 
        of throwing exception and try-catch blocks (more hints in the class)
        * fix tests 
            * rewrite `NewPersonRequestValidatorWorkshopTest` - note that 
            `NewPersonRequestValidatorWorkshopTest` could be helpful
    1. rewrite `PersonControllerWorkshop` using `Validation` instead
        of throwing exception and try-catch blocks (more hints in the class)
        * fix tests
            * rewrite `PersonControllerWorkshopTest` - note that
            `PersonControllerAnswerTest` could be helpful
    1. delete
        * `ErrorMessagesControllerAdvice`
        * `ValidationException`
* answers: `*Answer`
# theory in a nutshell

# conclusions in a nutshell