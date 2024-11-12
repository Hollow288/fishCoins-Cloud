package com.pond.build.service;

import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.willpower.Willpower;

import java.util.Map;

public interface WillpowerService {
    CommonResult<Willpower> addWillpower(Willpower willpower, TokenUser user);

    CommonResult<Map<String,Object>> willpowerByPage(Integer page, Integer pageSize, String searchName);
}
