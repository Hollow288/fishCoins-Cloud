package com.pond.build.remote.hotta.arms;

import com.pond.build.model.CommonResult;
import com.pond.build.remote.hotta.arms.fallback.ArmsRemoteFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name= "fishServiceApi", contextId = "armsServiceClient", fallback = ArmsRemoteFallBackService.class)
public interface ArmsRemote {
    @PostMapping("/add-arms")
    public Map<String,Object> addArms(@RequestBody Object arms);

    @GetMapping("/all-arms")
    public Map<String,Object> allArms(@RequestParam(value = "page") Integer page,
                                      @RequestParam(value = "pageSize") Integer pageSize,
                                      @RequestParam(value = "attributeType", defaultValue = "") String attributeType);
}
