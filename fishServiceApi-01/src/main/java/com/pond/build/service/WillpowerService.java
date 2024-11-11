package com.pond.build.service;

import com.pond.build.model.CommonResult;

import java.util.Map;

public interface WillpowerService {
    CommonResult<Map<String,Object>> willpowerByPage(Integer page, Integer pageSize, String searchName);
}
