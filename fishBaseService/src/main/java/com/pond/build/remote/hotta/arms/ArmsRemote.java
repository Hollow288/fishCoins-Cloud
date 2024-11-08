package com.pond.build.remote.hotta.arms;

import com.pond.build.model.CommonResult;
import com.pond.build.remote.hotta.arms.fallback.ArmsRemoteFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name= "fishServiceApi", contextId = "armsServiceClient", fallback = ArmsRemoteFallBackService.class)
public interface ArmsRemote {
    @PostMapping("/add-arms")
    Map<String,Object> addArms(@RequestBody Object arms);

    @PutMapping("/edit-arms")
    Map<String,Object> editArms(@RequestBody Object arms);

    @PutMapping (value = "/delete-arms")
    Map<String,Object> deleteArms(@RequestBody Object armsIds);

    @GetMapping("/page-arms")
    Map<String,Object> armsByPage(@RequestParam(value = "page") Integer page,
                                      @RequestParam(value = "page_size") Integer pageSize,
                                      @RequestParam(value = "attribute_type", defaultValue = "") String attributeType);

    @GetMapping("/id-arms/{arms_id}")
    Map<String, Object> armsById(@PathVariable(value = "arms_id") Integer armsId);
}
