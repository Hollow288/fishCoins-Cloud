package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.willpower.WillpowerClassificationMapper;
import com.pond.build.mapper.willpower.WillpowerMapper;
import com.pond.build.mapper.willpower.WillpowerSuitMapper;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.arms.Arms;
import com.pond.build.model.willpower.Willpower;
import com.pond.build.model.willpower.response.WillpowerBasic;
import com.pond.build.service.WillpowerService;
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
public class WillpowerServiceImpl implements WillpowerService {


    @Autowired
    private WillpowerMapper willpowerMapper;

    @Autowired
    private WillpowerClassificationMapper willpowerClassificationMapper;

    @Autowired
    private WillpowerSuitMapper willpowerSuitMapper;

    @Override
    public CommonResult<Willpower> addWillpower(Willpower willpower, TokenUser user) {
        willpower.setCreateBy(String.valueOf(user.getUserId()));
        willpowerMapper.insert(willpower);
        // 意志分类
        willpower.getWillpowerClassification().forEach(willpowerClassification -> willpowerClassification.setWillpowerId(willpower.getWillpowerId()));
        if (!CollectionUtils.isEmpty(willpower.getWillpowerClassification()))willpowerClassificationMapper.insertBatchSomeColumn(willpower.getWillpowerClassification());
        // 意志套装
        willpower.getWillpowerSuit().forEach(willpowerSuit -> willpowerSuit.setWillpowerId(willpower.getWillpowerId()));
        if (!CollectionUtils.isEmpty(willpower.getWillpowerSuit()))willpowerSuitMapper.insertBatchSomeColumn(willpower.getWillpowerSuit());
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功",willpower);
    }

    @Override
    public CommonResult<Willpower> editWillpower(Willpower willpower, TokenUser user) {
        willpower.setUpdateBy(String.valueOf(user.getUserId()));
        willpowerMapper.updateById(willpower);

        willpowerMapper.deleteDetailByWillpowerId(willpower.getWillpowerId());

        // 意志分类
        willpower.getWillpowerClassification().forEach(willpowerClassification -> willpowerClassification.setWillpowerId(willpower.getWillpowerId()));
        if (!CollectionUtils.isEmpty(willpower.getWillpowerClassification()))willpowerClassificationMapper.insertBatchSomeColumn(willpower.getWillpowerClassification());
        // 意志套装
        willpower.getWillpowerSuit().forEach(willpowerSuit -> willpowerSuit.setWillpowerId(willpower.getWillpowerId()));
        if (!CollectionUtils.isEmpty(willpower.getWillpowerSuit()))willpowerSuitMapper.insertBatchSomeColumn(willpower.getWillpowerSuit());

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功",willpower);
    }


    @Override
    public CommonResult<Object> deleteWillpower(Map<String, Object> willpowerIds, TokenUser user) {
        List<String> willpowerList = (List<String>)willpowerIds.get("willpowerIds");
        LambdaUpdateWrapper<Willpower> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(Willpower::getWillpowerId, willpowerList)
                .set(Willpower::getDelFlag, "1")
                .set(Willpower::getUpdateTime, new Date())
                .set(Willpower::getUpdateBy, user.getUserId());

        // 执行批量更新操作
        // 在 update(T entity, Wrapper<T> updateWrapper) 时，entity 不能为空，否则自动填充失效。
        boolean updateResult = willpowerMapper.update(null, updateWrapper) > 0;

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public CommonResult<Map<String,Object>> willpowerByPage(Integer page, Integer pageSize, String searchName) {
        int offset = (page - 1) * pageSize;
        int limit = pageSize;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", willpowerMapper.getCountWillpower(searchName));
        List<Willpower> allWillpowerInfoByPage = willpowerMapper.getWillpowerInfoByPage(offset,limit,searchName);
        resultMap.put("data", allWillpowerInfoByPage);
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);

        System.out.println(allWillpowerInfoByPage);
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",resultMap);
    }


    @Override
    public CommonResult<Willpower> willpowerById(Integer willpowerId) {
        Willpower willpower = willpowerMapper.getWillpowerInfoById(willpowerId);
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",willpower);
    }



}
