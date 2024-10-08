package com.pond.build.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/hello/{name}")
    public String test(@PathVariable(value = "name") String name){

        return "hello "+name+"，this is first messge--02";
    }

}
