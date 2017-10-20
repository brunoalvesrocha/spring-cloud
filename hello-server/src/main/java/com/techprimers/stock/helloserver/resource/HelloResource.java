package com.techprimers.stock.helloserver.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author brunorocha
 */

@RestController
@RequestMapping("/rest/hello/server")
public class HelloResource {

    @GetMapping
    public String hello()  {
        return "Hello World!";
    }
}
