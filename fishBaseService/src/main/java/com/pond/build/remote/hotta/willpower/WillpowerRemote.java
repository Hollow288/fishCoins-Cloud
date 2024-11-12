package com.pond.build.remote.hotta.willpower;

import com.pond.build.remote.hotta.arms.fallback.ArmsRemoteFallBackService;
import com.pond.build.remote.hotta.willpower.fallback.WillpowerRemoteFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name= "fishServiceApi", contextId = "willpowerServiceClient")
public interface WillpowerRemote {

    String PREFIX = "/willpower";

    @PostMapping(PREFIX + "/add-willpower")
    Map<String,Object> addWillpower(@RequestBody Object willpower);

    @GetMapping(PREFIX + "/page-willpower")
    Map<String,Object> willpowerByPage(@RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "page_size") Integer pageSize,
                                       @RequestParam(value = "search_name", defaultValue = "") String searchName);

}
