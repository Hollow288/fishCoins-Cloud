package com.pond.build.service;

import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.EventConsultation;
import com.pond.build.model.willpower.Willpower;

import java.util.Map;

public interface EventConsultationService {
    CommonResult<EventConsultation> addOrEditEventConsultation(EventConsultation eventConsultation, TokenUser user);

    CommonResult<Object> deleteEventConsultation(Map<String,Object> eventConsultationIds, TokenUser user);

    CommonResult<Map<String,Object>> eventConsultationByPage(Integer page, Integer pageSize, String searchName);

    CommonResult<EventConsultation> eventConsultationById(Integer eventConsultationId);
}
