package com.pond.build.hystrix;

import com.pond.build.remote.TestRemote;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class TestRemoteHystrix implements TestRemote {
    @Override
    public String hello(@RequestParam(value = "name") String name) {
        return "请求失败了,这是熔断器返回的结果";
    }
}
