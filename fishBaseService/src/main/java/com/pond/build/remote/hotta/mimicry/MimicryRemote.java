package com.pond.build.remote.hotta.mimicry;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name= "fishServiceApi", contextId = "mimicryServiceClient")
public interface MimicryRemote {

    String PREFIX = "/mimicry";

    @GetMapping(PREFIX + "/page-mimicry")
    Map<String,Object> mimicryByPage(@RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "page_size") Integer pageSize,
                                       @RequestParam(value = "search_name", defaultValue = "") String searchName);


    @PostMapping(PREFIX + "/add-mimicry")
    Map<String,Object> addMimicry(@RequestBody Object mimicry);


    @GetMapping(PREFIX + "/id-mimicry/{mimicry_id}")
    Map<String, Object> mimicryById(@PathVariable(value = "mimicry_id") Integer mimicryId);


    @PutMapping(PREFIX + "/edit-mimicry")
    Map<String,Object> editMimicry(@RequestBody Object mimicry);


    @PutMapping (value = PREFIX + "/delete-mimicry")
    Map<String,Object> deleteMimicry(@RequestBody Object mimicryIds);

}
