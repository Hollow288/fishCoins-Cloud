package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.mimicry.MimicryFavorsMapper;
import com.pond.build.mapper.mimicry.MimicryMapper;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.mimicry.Mimicry;
import com.pond.build.service.MimicryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MimicryServiceImpl implements MimicryService {

    @Autowired
    private MimicryMapper mimicryMapper;

    @Autowired
    private MimicryFavorsMapper mimicryFavorsMapper;

    @Override
    public CommonResult<Map<String,Object>> mimicryByPage(Integer page, Integer pageSize, String searchName) {

        Page<Mimicry> pages = new Page<>(page, pageSize);
        LambdaQueryWrapper<Mimicry> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Mimicry::getDelFlag, "0");
        lambdaQueryWrapper.like(Mimicry::getMimicryName,searchName);
        lambdaQueryWrapper.orderByAsc(Mimicry::getMimicryId);
        Page<Mimicry> mimicryPage = mimicryMapper.selectPage(pages, lambdaQueryWrapper);
        List<Mimicry> records = mimicryPage.getRecords();
        long total = mimicryPage.getTotal();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("data", records);
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",resultMap);
    }


    @Override
    public CommonResult<Mimicry> addMimicry(Mimicry mimicry, TokenUser user) {
        mimicry.setCreateBy(user.getUserId());
        mimicryMapper.insert(mimicry);
        mimicry.getMimicryFavors().forEach(n -> {
            n.setFavorsId(null);
            n.setMimicryId(mimicry.getMimicryId());
        });
        //好感度
        mimicryFavorsMapper.saveOrUpdateBatch(mimicry.getMimicryFavors());
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功",mimicry);
    }


    @Override
    public CommonResult<Mimicry> mimicryById(Integer mimicryId) {
        Mimicry mimicry = mimicryMapper.getMimicryInfoById(mimicryId);
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",mimicry);
    }

}
