package com.pond.build.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @GetMapping("/hello/{name}")
    public String test(@PathVariable(value = "name") String name){

        return "hello "+name+"，this is first messge--02";
    }

}
