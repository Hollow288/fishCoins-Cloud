package com.pond.build.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/hello/{name}")
    public String test(@PathVariable(value = "name") String name){

        if(name.equals("namee")){
            throw new RuntimeException("id不能为负数");
        }
        return "hello "+name+"，this is first messge--01";
    }

}
