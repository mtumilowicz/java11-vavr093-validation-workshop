[![Build Status](https://travis-ci.com/mtumilowicz/java11-vavr093-validation-workshop.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-vavr093-validation-workshop)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
# java11-vavr093-validation-workshop

# project description
* https://softwaremill.com/javaslang-data-validation/  
* https://www.vavr.io/vavr-docs/#_validation  
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
* The Validation control is an applicative functor and facilitates accumulating errors'
* When trying to compose Monads, the combination process will short circuit at the first encountered error
* But 'Validation' will continue processing the combining functions, accumulating all errors
* This is especially useful when doing validation of multiple fields, say a web form, and you want to know 
all errors encountered, instead of one at a time
* opposite to Bean Validation standard (JSR-303 and JSR-349)
* we dont need any infrastructure providers (AOP, dynamic proxies, DI)
* `interface Validation<E, T> extends Value<T>, Serializable`
    * `interface Value<T> extends Iterable<T>`
* two implementations:
    * `final class Valid<E, T> implements Validation<E, T>` - contains valid data
    * `final class Invalid<E, T> implements Validation<E, T>` - is an error representation
* If all of them are successful, the ap(fn) method maps all results to a single value using a function 
it takes as an argument
* It must expect the same number of parameters as the number of validation results passed to 
combine(...) method
* types of parameters must be the same as values contained by validation results
* The order of arguments passed to the function corresponds to the order of validation results
* If at least one of validation results points to invalid data, then ap(fn) methods returns Invalid instance, 
containing a list of all errors that occurred

# conclusions in a nutshell
* creating
* composing
* consuming