package com.pond.build.remote;

import com.pond.build.remote.fallback.TestRemoteFallBackService;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "fishServiceApi",fallback = TestRemoteFallBackService.class)
public interface TestRemote {

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable(value = "name") String name);
}
