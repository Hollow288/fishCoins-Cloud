package com.pond.build.controller;

import com.pond.build.remote.TestRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestRemote testRemote;


    @GetMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        String returnMsg = testRemote.hello(name);
        System.out.println(returnMsg);
        return returnMsg;
    }

}
