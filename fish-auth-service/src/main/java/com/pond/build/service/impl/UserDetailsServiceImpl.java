package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pond.build.mapper.UserMapper;
import com.pond.build.model.LoginUser;
import com.pond.build.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;



    //实现UserDetailsService接口，重写UserDetails方法，自定义用户的信息从数据中查询
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        //（认证，即校验该用户是否存在）查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);
        User user = userMapper.selectOne(queryWrapper);
        //如果没有查询到用户
        if (Objects.isNull(user)){
            throw new RuntimeException("用户名不存在！");
        }

        // (授权，即查询用户具有哪些权限)查询对应的用户信息
        //定义一个权限集合
//        List<String> permsList = menuMapper.selectPermsByUserId(user.getUserId());
        //定义个亿角色集合
//        List<String> rolesList = menuMapper.selectRolesByUserId(user.getUserId());

        List<String> resultList = new ArrayList<>();

//        resultList.addAll(permsList);
        //Todo 角色相关
        resultList.addAll(List.of("ROLE_ADMIN"));

        //把数据封装成UserDetails返回
        return new LoginUser(user,resultList);
    }
}

