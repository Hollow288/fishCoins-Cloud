package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.yuCoins.YuCoinsTaskTypeMapper;
import com.pond.build.mapper.yuCoins.YuCoinsTaskWeeklyMapper;
import com.pond.build.model.CommonResult;
import com.pond.build.model.yuCoins.YuCoinsTaskType;
import com.pond.build.model.yuCoins.YuCoinsTaskWeekly;
import com.pond.build.service.YuCoinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class YuCoinsServiceImpl  implements YuCoinsService {

    @Autowired
    private YuCoinsTaskWeeklyMapper yuCoinsTaskWeeklyMapper;

    @Autowired
    private YuCoinsTaskTypeMapper yuCoinsTaskTypeMapper;

    @Override
    public CommonResult<Map<String, Object>> yuCoinsWeeklyByPage(Integer page, Integer pageSize) {
        Page<YuCoinsTaskWeekly> pages = new Page<>(page, pageSize);
        LambdaQueryWrapper<YuCoinsTaskWeekly> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(YuCoinsTaskWeekly::getDelFlag, "0");
        lambdaQueryWrapper.orderByAsc(YuCoinsTaskWeekly::getTaskWeeklyId);
        Page<YuCoinsTaskWeekly> weeklyPage = yuCoinsTaskWeeklyMapper.selectPage(pages, lambdaQueryWrapper);
        List<YuCoinsTaskWeekly> records = weeklyPage.getRecords();
        long total = weeklyPage.getTotal();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("data", records);
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",resultMap);
    }


    @Override
    public CommonResult<List<YuCoinsTaskType>> yuCoinsTaskTypeInfo() {
        LambdaQueryWrapper<YuCoinsTaskType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(YuCoinsTaskType::getDelFlag, "0");
        List<YuCoinsTaskType> yuCoinsTaskTypes = yuCoinsTaskTypeMapper.selectList(lambdaQueryWrapper);

        Map<String, Object> resultMap = new HashMap<>();

        return new CommonResult<List<YuCoinsTaskType>>(HttpStatusCode.OK.getCode(),"查询成功",yuCoinsTaskTypes);
    }

    @Override
    public CommonResult<YuCoinsTaskWeekly> addEditYuCoinsWeekly(YuCoinsTaskWeekly yuCoinsTaskWeekly) {
        yuCoinsTaskWeeklyMapper.saveOrUpdate(yuCoinsTaskWeekly);
        return new CommonResult<YuCoinsTaskWeekly>(HttpStatusCode.OK.getCode(),"保存成功",yuCoinsTaskWeekly);
    }

    @Override
    public CommonResult<YuCoinsTaskWeekly> yuCoinsWeeklyById(Integer taskWeeklyId) {
        LambdaQueryWrapper<YuCoinsTaskWeekly> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(YuCoinsTaskWeekly::getTaskWeeklyId,taskWeeklyId);
        YuCoinsTaskWeekly yuCoinsTaskWeekly = yuCoinsTaskWeeklyMapper.selectOne(lambdaQueryWrapper);
        return new CommonResult<YuCoinsTaskWeekly>(HttpStatusCode.OK.getCode(),"查询成功",yuCoinsTaskWeekly);
    }

    @Override
    public CommonResult<Object> deleteYuCoinsWeekly(Map<String, Object> taskWeeklyIds) {
        List<String> taskWeeklyList = (List<String>)taskWeeklyIds.get("taskWeeklyIds");
        LambdaUpdateWrapper<YuCoinsTaskWeekly> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(YuCoinsTaskWeekly::getTaskWeeklyId, taskWeeklyList)
                .set(YuCoinsTaskWeekly::getDelFlag, "1");

        boolean updateResult = yuCoinsTaskWeeklyMapper.update(null, updateWrapper) > 0;

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功");
    }
}
