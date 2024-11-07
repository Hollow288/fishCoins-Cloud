package com.pond.build.service;

import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.arms.Arms;

import java.util.List;
import java.util.Map;

public interface ArmsService {
    CommonResult<Arms> addArms(Arms arms, TokenUser user);

    CommonResult<Map<String,Object>> armsByPage(Integer page, Integer pageSize, String attributeType);

    CommonResult<Arms> armsById(Integer armsId);
}
