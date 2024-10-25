package com.pond.build.service;

import com.pond.build.model.CommonResult;

import java.util.Map;

public interface AuthService {
    CommonResult loginByGithub(Map<String, Object> params);

    CommonResult loginBypassWord(Map<String, Object> params);
}
