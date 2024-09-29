package com.pond.build.controller;

import com.pond.build.remote.TestRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestRemote testRemote;


    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return testRemote.hello(name);
    }

}
