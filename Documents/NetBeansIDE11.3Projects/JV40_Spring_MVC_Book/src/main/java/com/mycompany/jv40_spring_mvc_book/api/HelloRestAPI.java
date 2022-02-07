/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jv40_spring_mvc_book.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ASUS
 */
@RestController
@RequestMapping("/api")
public class HelloRestAPI {

    @RequestMapping(value = "/hello-api",
            method = RequestMethod.GET)
    public Object helloWorld() {
        return "Hello Word";
    }

    @RequestMapping(value = "/request-param", method = RequestMethod.GET)
    public Object requestParam(
            @RequestParam(name = "country") String country) {
        return "Hello: " + country;
    }

    @RequestMapping(value = "/path-variable/{country}", method = RequestMethod.GET)
    public Object pathVariable(
            @PathVariable(name = "country") String country) {
        return "Hello: " + country;
    }
}
