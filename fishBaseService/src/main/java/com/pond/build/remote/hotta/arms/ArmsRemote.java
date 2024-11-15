package com.pond.build.remote.hotta.arms;

import com.pond.build.model.CommonResult;
import com.pond.build.remote.hotta.arms.fallback.ArmsRemoteFallBackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name= "fishServiceApi", contextId = "armsServiceClient", fallback = ArmsRemoteFallBackService.class)
public interface ArmsRemote {

    String PREFIX = "/arms";

    @PostMapping(PREFIX + "/add-arms")
    Map<String,Object> addArms(@RequestBody Object arms);

    @PutMapping(PREFIX + "/edit-arms")
    Map<String,Object> editArms(@RequestBody Object arms);

    @PutMapping (value = PREFIX + "/delete-arms")
    Map<String,Object> deleteArms(@RequestBody Object armsIds);

    @GetMapping(PREFIX + "/page-arms")
    Map<String,Object> armsByPage(@RequestParam(value = "page") Integer page,
                                      @RequestParam(value = "page_size") Integer pageSize,
                                      @RequestParam(value = "attribute_type", defaultValue = "") String attributeType);

    @GetMapping(PREFIX + "/id-arms/{arms_id}")
    Map<String, Object> armsById(@PathVariable(value = "arms_id") Integer armsId);


    @GetMapping(PREFIX + "/arms-mimicry-willpower")
    Map<String,Object> armsMimicryWillpower();


    @GetMapping(PREFIX + "/bind/{arms_id}/arms-mimicry-willpower")
    Map<String, Object> armsMimicryWillpowerBindInfo(@PathVariable(value = "arms_id") Integer armsId);

    @PutMapping(PREFIX + "/edit-arms-mimicry-willpower")
    Map<String,Object> editArmsMimicryWillpower(@RequestBody Object armsMimicryWillpower);
}
