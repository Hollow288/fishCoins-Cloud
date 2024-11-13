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

    @PutMapping(PREFIX + "/edit-willpower")
    Map<String,Object> editWillpower(@RequestBody Object willpower);


    @PutMapping (value = PREFIX + "/delete-willpower")
    Map<String,Object> deleteWillpower(@RequestBody Object willpowerIds);

    @GetMapping(PREFIX + "/page-willpower")
    Map<String,Object> willpowerByPage(@RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "page_size") Integer pageSize,
                                       @RequestParam(value = "search_name", defaultValue = "") String searchName);



    @GetMapping(PREFIX + "/id-willpower/{willpower_id}")
    Map<String, Object> willpowerById(@PathVariable(value = "willpower_id") Integer willpowerId);

}
