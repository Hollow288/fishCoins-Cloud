package com.pond.build.remote;

import com.pond.build.hystrix.TestRemoteHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "fishServiceApi",fallback = TestRemoteHystrix.class)
public interface TestRemote {

    @GetMapping(value = "/hello")
    public String hello(@RequestParam(value = "name") String name);
}
