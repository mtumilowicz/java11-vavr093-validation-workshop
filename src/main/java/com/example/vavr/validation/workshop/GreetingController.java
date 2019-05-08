package com.example.vavr.validation.workshop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mtumilowicz on 2019-05-08.
 */
@RestController
public class GreetingController {

    @RequestMapping("/")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return "hi!";
    }
}
