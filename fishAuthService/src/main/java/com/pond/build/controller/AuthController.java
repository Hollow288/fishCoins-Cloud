package com.pond.build.controller;


import com.pond.build.service.AuthService;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public CommonResult<TokenResponse> login(@RequestBody Map<String,Object> params){

        if(params.get("type") == null || params.get("type").equals("")){
            return new CommonResult<>(400,"请求参数错误");
        }

        if(params.get("type").equals("github")){
            return authService.loginByGithub(params);
        }


        return new CommonResult<>(400,"未知的登录方式");
    }
}
