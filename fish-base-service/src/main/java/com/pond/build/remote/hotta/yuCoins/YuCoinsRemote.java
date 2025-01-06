package com.pond.build.remote.hotta.yuCoins;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name= "fishServiceApi", contextId = "yuCoinsServiceClient")
public interface YuCoinsRemote {

    String PREFIX = "/yu-coins";

    @GetMapping(PREFIX + "/page-yu-coins-weekly")
    Map<String,Object> yuCoinsWeeklyByPage(@RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "page_size") Integer pageSize);


    @GetMapping(PREFIX + "/yu-coins-type")
    Map<String,Object> yuCoinsTaskTypeInfo();


    @PostMapping(PREFIX + "/add-edit-yu-coins-weekly")
    Map<String,Object> addEditYuCoinsWeekly(@RequestBody Object yuCoinsWeekly);


    @GetMapping(PREFIX + "/id-yu-coins-weekly/{task_weekly_id}")
    Map<String, Object> yuCoinsWeeklyById(@PathVariable(value = "task_weekly_id") Integer taskWeeklyId);

    @PutMapping (value = PREFIX + "/delete-yu-coins-weekly")
    Map<String,Object> deleteYuCoinsWeekly(@RequestBody Object taskWeeklyIds);

}
