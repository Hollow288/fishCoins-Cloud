package com.pond.build.controller.hotta;

import com.pond.build.aop.InjectUserDetails;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.EventConsultation;
import com.pond.build.model.willpower.Willpower;
import com.pond.build.service.EventConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/event-consultation")
public class EventConsultationController {

    @Autowired
    private EventConsultationService eventConsultationService;


    @PostMapping("/add-or-edit-event-consultation")
    @PreAuthorize("hasRole('ADMIN')")
    @InjectUserDetails
    public CommonResult<EventConsultation> addEventConsultation(@RequestBody EventConsultation eventConsultation, TokenUser user){
        return eventConsultationService.addOrEditEventConsultation(eventConsultation,user);
    }


    @GetMapping("/page-event-consultation")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Map<String,Object>> eventConsultationByPage(@RequestParam(value = "page") Integer page,
                                                       @RequestParam(value = "page_size") Integer pageSize,
                                                       @RequestParam(value = "search_name", defaultValue = "") String searchName){
        return eventConsultationService.eventConsultationByPage(page,pageSize,searchName);
    }

    @PutMapping("/delete-event-consultation")
    @PreAuthorize("hasRole('ADMIN')")
    @InjectUserDetails
    public CommonResult<Object> deleteEventConsultation(@RequestBody Map<String,Object> eventConsultationIds, TokenUser user){
        return eventConsultationService.deleteEventConsultation(eventConsultationIds,user);
    }

    @GetMapping("/id-event-consultation/{consultation_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<EventConsultation> eventConsultationById(@PathVariable(value = "consultation_id") Integer consultationId){
        return eventConsultationService.eventConsultationById(consultationId);
    }

}
