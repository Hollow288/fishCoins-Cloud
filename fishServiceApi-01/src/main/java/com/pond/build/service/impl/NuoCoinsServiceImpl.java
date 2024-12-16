package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.nuoCoins.NuoCoinsTaskTypeMapper;
import com.pond.build.mapper.nuoCoins.NuoCoinsTaskWeeklyDetailMapper;
import com.pond.build.mapper.nuoCoins.NuoCoinsTaskWeeklyMapper;
import com.pond.build.model.CommonResult;
import com.pond.build.model.nuoCoins.NuoCoinsTaskType;
import com.pond.build.model.nuoCoins.NuoCoinsTaskWeekly;
import com.pond.build.model.nuoCoins.NuoCoinsTaskWeeklyDetail;
import com.pond.build.service.NuoCoinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NuoCoinsServiceImpl implements NuoCoinsService {

    @Autowired
    private NuoCoinsTaskWeeklyMapper nuoCoinsTaskWeeklyMapper;

    @Autowired
    private NuoCoinsTaskTypeMapper nuoCoinsTaskTypeMapper;

    @Autowired
    private NuoCoinsTaskWeeklyDetailMapper nuoCoinsTaskWeeklyDetailMapper;

    @Override
    public CommonResult<Map<String, Object>> nuoCoinsWeeklyByPage(Integer page, Integer pageSize) {
        Page<NuoCoinsTaskWeekly> pages = new Page<>(page, pageSize);
        LambdaQueryWrapper<NuoCoinsTaskWeekly> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(NuoCoinsTaskWeekly::getDelFlag, "0");
        lambdaQueryWrapper.orderByAsc(NuoCoinsTaskWeekly::getTaskWeeklyId);
        Page<NuoCoinsTaskWeekly> weeklyPage = nuoCoinsTaskWeeklyMapper.selectPage(pages, lambdaQueryWrapper);
        List<NuoCoinsTaskWeekly> records = weeklyPage.getRecords();
        long total = weeklyPage.getTotal();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("data", records);
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",resultMap);
    }


    @Override
    public CommonResult<List<NuoCoinsTaskType>> nuoCoinsTaskTypeInfo() {
        LambdaQueryWrapper<NuoCoinsTaskType> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(NuoCoinsTaskType::getDelFlag, "0");
        List<NuoCoinsTaskType> nuoCoinsTaskTypes = nuoCoinsTaskTypeMapper.selectList(lambdaQueryWrapper);

        Map<String, Object> resultMap = new HashMap<>();

        return new CommonResult<List<NuoCoinsTaskType>>(HttpStatusCode.OK.getCode(),"查询成功",nuoCoinsTaskTypes);
    }

    @Override
    public CommonResult<NuoCoinsTaskWeekly> addEditNuoCoinsWeekly(NuoCoinsTaskWeekly nuoCoinsTaskWeekly) {
        nuoCoinsTaskWeeklyMapper.saveOrUpdate(nuoCoinsTaskWeekly);
        // 之前所有的详情
        LambdaUpdateWrapper<NuoCoinsTaskWeeklyDetail> allDetailList = new LambdaUpdateWrapper<NuoCoinsTaskWeeklyDetail>().eq(NuoCoinsTaskWeeklyDetail::getTaskWeeklyId, nuoCoinsTaskWeekly.getTaskWeeklyId());
        List<NuoCoinsTaskWeeklyDetail> nuoCoinsTaskWeeklyDetails = nuoCoinsTaskWeeklyDetailMapper.selectList(allDetailList);
        List<Integer> allDetailIdList = nuoCoinsTaskWeeklyDetails.stream().map(NuoCoinsTaskWeeklyDetail::getWeeklyDetailId).toList();
        // 修改后所有的详情ID
        List<Integer> thisDetailIds = nuoCoinsTaskWeekly.getWeeklyDetailIds();

        // 需要删除的数据
        List<Integer> willDelete = allDetailIdList.stream().filter(n -> !thisDetailIds.contains(n)).toList();
        if(!willDelete.isEmpty()){
            LambdaUpdateWrapper<NuoCoinsTaskWeeklyDetail> willDeleteWrapper = new LambdaUpdateWrapper<NuoCoinsTaskWeeklyDetail>()
                    .in(NuoCoinsTaskWeeklyDetail::getWeeklyDetailId, willDelete)
                    .set(NuoCoinsTaskWeeklyDetail::getDelFlag, "1");
            int update = nuoCoinsTaskWeeklyDetailMapper.update(willDeleteWrapper);
        }

        thisDetailIds.forEach(n->{
            if(!allDetailIdList.contains(n)){
                NuoCoinsTaskWeeklyDetail nuoCoinsTaskWeeklyDetail = new NuoCoinsTaskWeeklyDetail();
                nuoCoinsTaskWeeklyDetail.setTaskWeeklyId(nuoCoinsTaskWeekly.getTaskWeeklyId());
                nuoCoinsTaskWeeklyDetail.setTaskTypeId(n);
                nuoCoinsTaskWeeklyDetailMapper.saveOrUpdate(nuoCoinsTaskWeeklyDetail);
            }
        });

        return new CommonResult<NuoCoinsTaskWeekly>(HttpStatusCode.OK.getCode(),"保存成功",nuoCoinsTaskWeekly);
    }

    @Override
    public CommonResult<NuoCoinsTaskWeekly> nuoCoinsWeeklyById(Integer taskWeeklyId) {
        LambdaQueryWrapper<NuoCoinsTaskWeekly> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(NuoCoinsTaskWeekly::getTaskWeeklyId,taskWeeklyId);
        NuoCoinsTaskWeekly nuoCoinsTaskWeekly = nuoCoinsTaskWeeklyMapper.selectOne(lambdaQueryWrapper);

        LambdaQueryWrapper<NuoCoinsTaskWeeklyDetail> lambdaQueryWrapperDetail = new LambdaQueryWrapper<>();
        lambdaQueryWrapperDetail.eq(NuoCoinsTaskWeeklyDetail::getTaskWeeklyId,taskWeeklyId).eq(NuoCoinsTaskWeeklyDetail::getDelFlag,"0");
        List<NuoCoinsTaskWeeklyDetail> nuoCoinsTaskWeeklyDetails = nuoCoinsTaskWeeklyDetailMapper.selectList(lambdaQueryWrapperDetail);
        List<Integer> weeklyDetailIds = nuoCoinsTaskWeeklyDetails.stream().map(NuoCoinsTaskWeeklyDetail::getWeeklyDetailId).toList();
        nuoCoinsTaskWeekly.setWeeklyDetailIds(weeklyDetailIds);
        return new CommonResult<NuoCoinsTaskWeekly>(HttpStatusCode.OK.getCode(),"查询成功",nuoCoinsTaskWeekly);
    }

    @Override
    public CommonResult<Object> deleteNuoCoinsWeekly(Map<String, Object> taskWeeklyIds) {
        List<String> taskWeeklyList = (List<String>)taskWeeklyIds.get("taskWeeklyIds");
        LambdaUpdateWrapper<NuoCoinsTaskWeekly> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(NuoCoinsTaskWeekly::getTaskWeeklyId, taskWeeklyList)
                .set(NuoCoinsTaskWeekly::getDelFlag, "1");

        boolean updateResult = nuoCoinsTaskWeeklyMapper.update(null, updateWrapper) > 0;

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功");
    }
}
