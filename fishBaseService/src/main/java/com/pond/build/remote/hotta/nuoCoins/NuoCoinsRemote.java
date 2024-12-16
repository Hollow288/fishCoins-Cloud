package com.pond.build.remote.hotta.nuoCoins;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name= "fishServiceApi", contextId = "nuoCoinsServiceClient")
public interface NuoCoinsRemote {

    String PREFIX = "/nuo-coins";

    @GetMapping(PREFIX + "/page-nuo-coins-weekly")
    Map<String,Object> nuoCoinsWeeklyByPage(@RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "page_size") Integer pageSize);


    @GetMapping(PREFIX + "/nuo-coins-type")
    Map<String,Object> nuoCoinsTaskTypeInfo();


    @PostMapping(PREFIX + "/add-edit-nuo-coins-weekly")
    Map<String,Object> addEditNuoCoinsWeekly(@RequestBody Object nuoCoinsWeekly);


    @GetMapping(PREFIX + "/id-nuo-coins-weekly/{task_weekly_id}")
    Map<String, Object> nuoCoinsWeeklyById(@PathVariable(value = "task_weekly_id") Integer taskWeeklyId);

    @PutMapping (value = PREFIX + "/delete-nuo-coins-weekly")
    Map<String,Object> deleteNuoCoinsWeekly(@RequestBody Object taskWeeklyIds);

}
