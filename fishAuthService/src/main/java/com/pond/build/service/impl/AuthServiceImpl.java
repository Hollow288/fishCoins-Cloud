package com.pond.build.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.pond.build.service.AuthService;
import com.pond.build.utils.JwtUtil;
import com.pond.build.utils.RedisUtil;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RedisUtil redisUtil;

    @Value("${oauth2.github.clientId:}")
    private String clientId;

    @Value("${oauth2.github.clientSecret:}")
    private String clientSecret;

    @Override
    public CommonResult<TokenResponse> loginByGithub(Map<String, Object> params){

        if(params.get("code") == null || params.get("code").equals("")){
            return new CommonResult<>(400,"请求参数错误");
        }
//        Config config = ConfigService.getConfig("fishAuthService"); // 获取默认 application 命名空间
//        String clientId = config.getProperty("oauth2.github.clientId", null);
//        String clientSecret = config.getProperty("oauth2.github.clientSecret", null);
        System.out.println(clientId);
        if(StringUtils.isNotBlank(clientId) && StringUtils.isNotBlank(clientSecret)){

//            Map<String, Object > paramMap = new HashMap<>();
//            paramMap.put("client_id",clientId);
//            paramMap.put("client_secret",clientSecret);
//            paramMap.put("code",params.get("code"));
//            paramMap.put("accept","json");
//
//            String result = HttpUtil.post("https://github.com/login/oauth/access_token",paramMap);
//            String token = result.split("&")[0].split("=")[1];
//            // 获取用户信息
//            String finalResult = HttpRequest.get("https://api.github.com/user")
//                    .header("Authorization","token "+token)
//                    .header("X-GitHub-Api-Version", "2022-11-28")
//                    .execute().body();
//            System.out.println(finalResult);
//            //userName
//            JSONObject githubUserInfo = JSON.parseObject(finalResult);

            //Todo 这里先不引入角色、权限相关的代码 仅仅用github的ID对比



            redisUtil.set("access_token:"+ "1",JSONObject.toJSONString("12345612"), JwtUtil.JWT_ACCESS_TTL/1000);
            redisUtil.set("refresh_token:"+ "2", JSONObject.toJSONString("12345612"),JwtUtil.JWT_REFRESH_TTL/1000);


            return null;
        }else{
            return new CommonResult<>(500,"服务器内部错误");
        }
    }
}
