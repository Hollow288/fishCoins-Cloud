package com.pond.build.service;

import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.mimicry.Mimicry;
import com.pond.build.model.yuCoins.YuCoinsTaskType;
import com.pond.build.model.yuCoins.YuCoinsTaskWeekly;

import java.util.List;
import java.util.Map;

public interface YuCoinsService {
    CommonResult<Map<String,Object>> yuCoinsWeeklyByPage(Integer page, Integer pageSize);

    CommonResult<List<YuCoinsTaskType>> yuCoinsTaskTypeInfo();

    CommonResult<YuCoinsTaskWeekly> addEditYuCoinsWeekly(YuCoinsTaskWeekly yuCoinsTaskWeekly);

    CommonResult<YuCoinsTaskWeekly> yuCoinsWeeklyById(Integer taskWeeklyId);

    CommonResult<Object> deleteYuCoinsWeekly(Map<String, Object> taskWeeklyIds);
}
