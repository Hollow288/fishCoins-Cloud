package com.pond.build.remote.fallback;

import com.pond.build.remote.TestRemote;
import org.springframework.stereotype.Component;

@Component
public class TestRemoteFallBackService implements TestRemote {
    @Override
    public String hello(String name) {
        return "请求失败了，这是默认的返回结果/(ㄒoㄒ)/~~";
    }
}
