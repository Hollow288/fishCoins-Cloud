package com.pond.build.remote.hotta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name= "fishServiceApi", contextId = "eventConsultationServiceClient")
public interface EventConsultationRemote {

    String PREFIX = "/event-consultation";

    @PostMapping(PREFIX + "/add-or-edit-event-consultation")
    Map<String,Object> addOrEditEventConsultation(@RequestBody Object eventConsultation);


    @PutMapping (value = PREFIX + "/delete-event-consultation")
    Map<String,Object> deleteEventConsultation(@RequestBody Object eventConsultationIds);

    @GetMapping(PREFIX + "/page-event-consultation")
    Map<String,Object> eventConsultationByPage(@RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "page_size") Integer pageSize,
                                       @RequestParam(value = "search_name", defaultValue = "") String searchName);

    @GetMapping(PREFIX + "/id-event-consultation/{consultation_id}")
    Map<String, Object> eventConsultationById(@PathVariable(value = "consultation_id") Integer consultationId);

}
