package com.natsuki_kining.gupao.v1.microservices.spring.rest.demo.controller;

import com.natsuki_kining.gupao.v1.microservices.spring.rest.demo.domain.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonRestController {

    @GetMapping("/person/{id}")
    public Person persion(@PathVariable Long id,
                          @RequestParam(required = false) String name){
        Person person = new Person(id,name);
        return person;
    }

    @PostMapping(value = "/person/json/to/properties",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = "application/properties+person"
             )
    public Person personJSONToProperties(@RequestBody Person person){
        return person;
    }

    @PostMapping(value = "/person/properties/to/json",
            consumes = "application/properties+person",   //对应content-type
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE  //对应accept
             )
    public Person personPropertiesToJSON(@RequestBody Person person){
        return person;
    }
}
