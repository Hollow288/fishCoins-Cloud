package com.pond.build.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
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


    @GetMapping("/say")
    public String index2() {
        Config config = ConfigService.getAppConfig(); // 获取默认 application 命名空间
        String someKey = config.getProperty("configValue", "default_value");
        System.out.println("Config Value: " + someKey);
        return someKey;
    }


    public String indexFallback(String name,Throwable t) {
        return "请求失败了，这是默认的返回结果/(ㄒoㄒ)/~~";
    }

}
