package com.pond.build.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "fishServiceApi")
public interface TestRemote {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable(value = "name") String name);
}
