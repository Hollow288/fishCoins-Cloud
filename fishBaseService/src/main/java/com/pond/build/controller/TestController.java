package com.pond.build.controller;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.pond.build.remote.TestRemote;
import com.pond.build.remote.fallback.TestRemoteFallBackService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestRemote testRemote;

    @Value(value = "${configValue:}")
    private String configValue;


    @GetMapping("/hello/{name}")
    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/say3")
    @PreAuthorize("hasRole('ADMIN')")
    public String index3() {
        Config config = ConfigService.getConfig("fishBaseService"); // 获取默认 application 命名空间
        String someKey = config.getProperty("configValue", "default_value");
        System.out.println("Config Value: " + someKey);
        System.out.println(configValue);
        return someKey;
    }




}
