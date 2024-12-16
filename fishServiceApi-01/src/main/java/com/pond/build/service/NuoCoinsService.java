package com.pond.build.service;

import com.pond.build.model.CommonResult;
import com.pond.build.model.nuoCoins.NuoCoinsTaskType;
import com.pond.build.model.nuoCoins.NuoCoinsTaskWeekly;

import java.util.List;
import java.util.Map;

public interface NuoCoinsService {
    CommonResult<Map<String,Object>> nuoCoinsWeeklyByPage(Integer page, Integer pageSize);

    CommonResult<List<NuoCoinsTaskType>> nuoCoinsTaskTypeInfo();

    CommonResult<NuoCoinsTaskWeekly> addEditNuoCoinsWeekly(NuoCoinsTaskWeekly nuoCoinsTaskWeekly);

    CommonResult<NuoCoinsTaskWeekly> nuoCoinsWeeklyById(Integer taskWeeklyId);

    CommonResult<Object> deleteNuoCoinsWeekly(Map<String, Object> taskWeeklyIds);
}
