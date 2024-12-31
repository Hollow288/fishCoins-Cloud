package com.pond.build.controller.hotta;


import com.pond.build.remote.hotta.EventConsultationRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/event-consultation")
public class EventConsultationController {

    @Autowired
    private EventConsultationRemote eventConsultationRemote;

    @PostMapping("/add-or-edit-event-consultation")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> addOrEditEventConsultation(@RequestBody Object eventConsultation){
        return eventConsultationRemote.addOrEditEventConsultation(eventConsultation);
    }


    @PutMapping("/delete-event-consultation")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> deleteEventConsultation(@RequestBody Object eventConsultationIds){
        return eventConsultationRemote.deleteEventConsultation(eventConsultationIds);
    }


    @GetMapping("/page-event-consultation")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> eventConsultationByPage(@RequestParam(value = "page") Integer page,
                                                       @RequestParam(value = "page_size") Integer pageSize,
                                                       @RequestParam(value = "search_name", defaultValue = "") String searchName){
        return eventConsultationRemote.eventConsultationByPage(page,pageSize,searchName);
    }


    @GetMapping("/id-event-consultation/{consultation_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> eventConsultationById(@PathVariable(value = "consultation_id") Integer consultationId){
        return eventConsultationRemote.eventConsultationById(consultationId);
    }


}
