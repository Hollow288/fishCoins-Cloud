package com.pond.build.service;

import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.arms.Arms;

import java.util.List;

public interface ArmsService {
    CommonResult<Arms> addArms(Arms arms, TokenUser user);

    CommonResult<List<Arms>> allArms(Integer page, Integer pageSize, String attributeType);
}
