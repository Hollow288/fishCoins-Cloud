package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.EventConsultationMapper;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.EventConsultation;
import com.pond.build.model.mimicry.Mimicry;
import com.pond.build.model.willpower.Willpower;
import com.pond.build.model.yuCoins.YuCoinsTaskWeekly;
import com.pond.build.service.EventConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EventConsultationServiceImpl implements EventConsultationService {


    @Autowired
    private EventConsultationMapper eventConsultationMapper;



    @Override
    public CommonResult<EventConsultation> addOrEditEventConsultation(EventConsultation eventConsultation, TokenUser user) {
        eventConsultationMapper.saveOrUpdate(eventConsultation);
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功",eventConsultation);
    }


    @Override
    public CommonResult<Object> deleteEventConsultation(Map<String, Object> eventConsultationIds, TokenUser user) {
        List<String> eventConsultationList = (List<String>)eventConsultationIds.get("eventConsultationIds");
        LambdaUpdateWrapper<EventConsultation> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(EventConsultation::getConsultationId, eventConsultationList)
                .set(EventConsultation::getDelFlag, "1");

        // 执行批量更新操作
        // 在 update(T entity, Wrapper<T> updateWrapper) 时，entity 不能为空，否则自动填充失效。
        boolean updateResult = eventConsultationMapper.update(null, updateWrapper) > 0;

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public CommonResult<Map<String,Object>> eventConsultationByPage(Integer page, Integer pageSize, String searchName) {

        Page<EventConsultation> pages = new Page<>(page, pageSize);
        LambdaQueryWrapper<EventConsultation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(EventConsultation::getDelFlag, "0");
        lambdaQueryWrapper.like(EventConsultation::getConsultationTitle,searchName);
        lambdaQueryWrapper.orderByAsc(EventConsultation::getConsultationId);
        Page<EventConsultation> eventConsultationPage = eventConsultationMapper.selectPage(pages, lambdaQueryWrapper);
        List<EventConsultation> records = eventConsultationPage.getRecords();
        long total = eventConsultationPage.getTotal();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("data", records);
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);


        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",resultMap);
    }


    @Override
    public CommonResult<EventConsultation> eventConsultationById(Integer consultationId) {
        LambdaQueryWrapper<EventConsultation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(EventConsultation::getConsultationId,consultationId);
        EventConsultation eventConsultation = eventConsultationMapper.selectOne(lambdaQueryWrapper);
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",eventConsultation);
    }





}
