package com.pond.build.service;

import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenResponse;

import java.util.Map;

public interface AuthService {
    CommonResult<TokenResponse> loginByGithub(Map<String, Object> params);
}
