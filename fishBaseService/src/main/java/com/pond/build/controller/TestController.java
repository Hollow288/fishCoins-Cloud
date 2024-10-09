package com.pond.build.controller;

import com.pond.build.remote.TestRemote;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    @CircuitBreaker(name = "fishServiceApi", fallbackMethod = "indexFallback")
    public String index(@PathVariable("name") String name) {
        String returnMsg = testRemote.hello(name);
        System.out.println(returnMsg);
        return returnMsg;
    }


    public String indexFallback(String name,Throwable t) {
        return "请求失败了，这是默认的返回结果/(ㄒoㄒ)/~~";
    }

}
