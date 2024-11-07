package com.pond.build.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pond.build.mapper.UserMapper;
import com.pond.build.model.LoginUser;
import com.pond.build.model.Response.UserResponse;
import com.pond.build.model.TokenUser;
import com.pond.build.model.User;
import com.pond.build.service.AuthService;
import com.pond.build.utils.JwtUtil;
import com.pond.build.utils.RedisUtil;
import com.pond.build.model.CommonResult;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.*;

import  com.pond.build.enums.HttpStatusCode;


@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Value("${oauth2.github.clientId:}")
    private String clientId;

    @Value("${oauth2.github.clientSecret:}")
    private String clientSecret;

    @Override
    public CommonResult loginByGithub(Map<String, Object> params){

        if(params.get("code") == null || params.get("code").equals("")){
            return new CommonResult<>(HttpStatusCode.FORBIDDEN_ROLE_ERR.getCode(),HttpStatusCode.FORBIDDEN_ROLE_ERR.getCnMessage());
        }
//        Config config = ConfigService.getConfig("fishAuthService"); // 获取默认 application 命名空间
//        String clientId = config.getProperty("oauth2.github.clientId", null);
//        String clientSecret = config.getProperty("oauth2.github.clientSecret", null);
        System.out.println(clientId);
        if(StringUtils.isNotBlank(clientId) && StringUtils.isNotBlank(clientSecret)){

            Map<String, Object > paramMap = new HashMap<>();
            paramMap.put("client_id",clientId);
            paramMap.put("client_secret",clientSecret);
            paramMap.put("code",params.get("code"));
            paramMap.put("accept","json");

            String result = HttpUtil.post("https://github.com/login/oauth/access_token",paramMap);
            String token = result.split("&")[0].split("=")[1];
            // 获取用户信息
            String finalResult = HttpRequest.get("https://api.github.com/user")
                    .header("Authorization","token "+token)
                    .header("X-GitHub-Api-Version", "2022-11-28")
                    .execute().body();
            System.out.println(finalResult);
            //userName
            JSONObject githubUserInfo = JSON.parseObject(finalResult);
            String id = String.valueOf(githubUserInfo.get("id"));
            //Todo 这里先不引入角色、权限相关的代码 仅仅用github的ID对比

            QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("github_id",id);
            User users = userMapper.selectOne(userQueryWrapper);

            if (Objects.isNull(users)){
//                throw new RuntimeException("用户名不存在！");
                return new CommonResult<>(HttpStatusCode.NOT_FOUND_USERNAME.getCode(),HttpStatusCode.NOT_FOUND_USERNAME.getCnMessage());
            }
            String userId = users.getUserId().toString();
            TokenUser tokenUser = new TokenUser();
            tokenUser.setUserId(users.getUserId());
            //Todo 往tokenUser添加权限信息
            tokenUser.setPermissions(new ArrayList<String>(List.of("ROLE_ADMIN")));

            String accessToken = jwtUtil.createJWT(JSONObject.toJSONString(tokenUser), JwtUtil.JWT_ACCESS_TTL);
            String refreshToken = jwtUtil.createJWT(JSONObject.toJSONString(tokenUser), JwtUtil.JWT_REFRESH_TTL);

            Map<String, Object> map = new HashMap<>();
            map.put("access_token",accessToken);
            map.put("refresh_token",refreshToken);

            LoginUser loginUser = new LoginUser();
            loginUser.setUser(users);
            map.put("user",this.makeUserResponse(loginUser.getUser()));
            //Todo 往loginUser添加权限信息
            loginUser.setPermissions(new ArrayList<String>(List.of("ROLE_ADMIN")));

            redisUtil.set("access_token:"+ userId,JSONObject.toJSONString(loginUser), JwtUtil.JWT_ACCESS_TTL/1000);
            redisUtil.set("refresh_token:"+ userId, JSONObject.toJSONString(loginUser),JwtUtil.JWT_REFRESH_TTL/1000);


            return new CommonResult<>(HttpStatusCode.OK.getCode(),"登录成功",map);
        }else{
            return new CommonResult<>(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        }
    }

    @Override
    public CommonResult loginBypassWord(Map<String, Object> params) {

        if(params.get("userName") == null || params.get("userName").equals("") || params.get("passWord") == null || params.get("passWord").equals("")){
            return new CommonResult<>(HttpStatusCode.USERNAME_PASSWORD_ERR.getCode(),HttpStatusCode.USERNAME_PASSWORD_ERR.getCnMessage());
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(params.get("userName"),params.get("passWord"));

        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            return new CommonResult<>(HttpStatusCode.USERNAME_PASSWORD_ERR.getCode(),HttpStatusCode.USERNAME_PASSWORD_ERR.getCnMessage());
        }


        if (Objects.isNull(authenticate)){
//            throw new RuntimeException("登录失败");
            return new CommonResult<>(HttpStatusCode.USERNAME_PASSWORD_ERR.getCode(),HttpStatusCode.USERNAME_PASSWORD_ERR.getCnMessage());
        }


        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();

        String userid = loginUser.getUser().getUserId().toString();
        TokenUser tokenUser = new TokenUser();
        tokenUser.setUserId(loginUser.getUser().getUserId());
        //Todo 往tokenUser添加权限信息
        tokenUser.setPermissions(new ArrayList<String>(List.of("ROLE_ADMIN")));

//        String jwt = JwtUtil.createJWT(userid);
        String accessToken = jwtUtil.createJWT(JSONObject.toJSONString(tokenUser), JwtUtil.JWT_ACCESS_TTL);
        String refreshToken = jwtUtil.createJWT(JSONObject.toJSONString(tokenUser), JwtUtil.JWT_REFRESH_TTL);

        Map<String, Object> map = new HashMap<>();
        map.put("access_token",accessToken);
        map.put("refresh_token",refreshToken);
        //
        map.put("user",this.makeUserResponse(loginUser.getUser()));



        redisUtil.set("access_token:"+ userid,JSONObject.toJSONString(loginUser),JwtUtil.JWT_ACCESS_TTL/1000);
        redisUtil.set("refresh_token:"+ userid,JSONObject.toJSONString(loginUser),JwtUtil.JWT_REFRESH_TTL/1000);

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"登录成功",map);
    }


    @Override
    public CommonResult refreshToken(String refreshToken) {

        try {

            //检查refreshToken是否过期
            Boolean tokenExpired = jwtUtil.isTokenExpired(refreshToken);
            if(tokenExpired){
                return new CommonResult<>(HttpStatusCode.REFRESH_TOKEN_ERR.getCode(),HttpStatusCode.REFRESH_TOKEN_ERR.getCnMessage());
            }
            //检查refreshToken是否在黑名单
            if(redisUtil.isBlackToken(refreshToken)){
                return new CommonResult<>(HttpStatusCode.REFRESH_TOKEN_ERR.getCode(),HttpStatusCode.REFRESH_TOKEN_ERR.getCnMessage());
            }

            Claims claims = jwtUtil.parseJWT(refreshToken);
            TokenUser thisTokenUser = JSONObject.parseObject(claims.getSubject(), TokenUser.class);
            //检查refreshToken是否在redis中
            if(!redisUtil.hasKey("refresh_token:"+ thisTokenUser.getUserId())){
                return new CommonResult<>(HttpStatusCode.REFRESH_TOKEN_ERR.getCode(),HttpStatusCode.REFRESH_TOKEN_ERR.getCnMessage(),null);
            }

            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUserId,thisTokenUser.getUserId());
            User user = userMapper.selectOne(queryWrapper);

            TokenUser tokenUser = new TokenUser();
            tokenUser.setUserId(user.getUserId());
            //Todo 往tokenUser添加权限信息
            tokenUser.setPermissions(new ArrayList<String>(List.of("ROLE_ADMIN")));

            //重置AccessToken和RefreshToken过期时间(秒)
            redisUtil.set("access_token:"+ user.getUserId(),JSONObject.toJSONString(tokenUser),JwtUtil.JWT_ACCESS_TTL/1000);
            redisUtil.set("refresh_token:"+ user.getUserId(),JSONObject.toJSONString(tokenUser),JwtUtil.JWT_REFRESH_TTL/1000);

            //生成新的AccessToken和RefreshToken
            Map<String, String> map = new HashMap<>();
            String newAccessToken = jwtUtil.createJWT(JSONObject.toJSONString(tokenUser), JwtUtil.JWT_ACCESS_TTL);
            String newRefreshToken = jwtUtil.createJWT(JSONObject.toJSONString(tokenUser), JwtUtil.JWT_REFRESH_TTL);
            //将refreshToken加入黑名单
            redisUtil.tokenAddToBlack(refreshToken);
            //将newAccessToken和newRefreshToken返回给前端
            map.put("access_token",newAccessToken);
            map.put("refresh_token",newRefreshToken);

            return new CommonResult<>(HttpStatusCode.OK.getCode(),"刷新成功",map);

        } catch (Exception e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
            return new CommonResult<>(HttpStatusCode.REFRESH_TOKEN_ERR.getCode(),HttpStatusCode.REFRESH_TOKEN_ERR.getCnMessage());
        }

    }



    private UserResponse makeUserResponse(User user){
        UserResponse userResponse = new UserResponse();
        BeanUtil.copyProperties(user, userResponse);
        userResponse.setAvatarUrl(userResponse.getAvatarUrl());
        return userResponse;
    }

}
