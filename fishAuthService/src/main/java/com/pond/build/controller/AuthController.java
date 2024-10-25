package com.pond.build.controller;


import com.pond.build.enums.HttpStatusCode;
import com.pond.build.service.AuthService;
import com.pond.build.model.CommonResult;
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
    public CommonResult login(@RequestBody Map<String,Object> params){

        if(params.get("type") == null || params.get("type").equals("")){
            return new CommonResult<>(HttpStatusCode.UNKNOWN_LOGIN_TYPE.getCode(),HttpStatusCode.UNKNOWN_LOGIN_TYPE.getCnMessage());
        }

        if(params.get("type").equals("github")){
            return authService.loginByGithub(params);
        }

        if(params.get("type").equals("password")){
            return authService.loginBypassWord(params);
        }


        return new CommonResult<>(HttpStatusCode.UNKNOWN_LOGIN_TYPE.getCode(),HttpStatusCode.UNKNOWN_LOGIN_TYPE.getCnMessage());
    }
}
