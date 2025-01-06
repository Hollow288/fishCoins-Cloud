package com.pond.build.service;

import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.mimicry.Mimicry;

import java.util.Map;

public interface MimicryService {
    CommonResult<Map<String,Object>> mimicryByPage(Integer page, Integer pageSize, String searchName);

    CommonResult<Mimicry> addMimicry(Mimicry mimicry, TokenUser user);

    CommonResult<Mimicry> mimicryById(Integer mimicryId);

    CommonResult<Mimicry> editMimicry(Mimicry mimicry, TokenUser user);

    CommonResult<Object> deleteMimicry(Map<String, Object> mimicryIds, TokenUser user);
}
