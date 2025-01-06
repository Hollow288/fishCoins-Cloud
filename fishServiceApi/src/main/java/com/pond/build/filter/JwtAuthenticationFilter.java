package com.pond.build.filter;

import com.alibaba.fastjson2.JSONObject;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.utils.HmacUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value(value = "${gateway.key:}")
    private String gatewayKey;

    @Value(value = "${gateway.maxTime:}")
    private long maxTime;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String timestamp = request.getHeader("X-Timestamp");
        String signature = request.getHeader("X-Gateway-Signature");
        String userRoles = request.getHeader("X-User-Roles");

        // 验证签名
        if (!isValidSignature(timestamp, signature)) {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(JSONObject.toJSONString(new CommonResult<>(HttpStatusCode.ACCESS_TOKEN_ERR.getCode(),HttpStatusCode.ACCESS_TOKEN_ERR.getCnMessage())));
            return;
        }



        // 设置用户角色到 SecurityContext
        setUserRolesInSecurityContext(userRoles);

        // 放行请求
        filterChain.doFilter(request, response);
    }

    private boolean isValidSignature(String timestamp, String signature) {
        // 验证时间戳是否在有效时间范围内
        if (!isTimestampValid(timestamp)) {
            return false;
        }
        String expectedSignature = HmacUtils.generateHMAC(timestamp, gatewayKey);
        return expectedSignature.equals(signature);
    }


    private void setUserRolesInSecurityContext(String userRoles) {
        TokenUser tokenUser = JSONObject.parseObject(userRoles, TokenUser.class);

        UserDetails userDetails = createUserDetails(tokenUser);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetails createUserDetails(TokenUser tokenUser) {
        // 创建包含用户角色的 UserDetails（你可以自定义实现 UserDetails）
        return new org.springframework.security.core.userdetails.User(tokenUser.getUserId().toString(), "", AuthorityUtils.createAuthorityList(tokenUser.getPermissions()));
    }


    private boolean isTimestampValid(String timestamp) {
        try {
            // 解析时间戳（毫秒级）
            long requestTime = Long.parseLong(timestamp);
            long now = System.currentTimeMillis();

            // 计算时间差（毫秒）
            long diff = Math.abs(now - requestTime);
            return diff <= maxTime;
        } catch (NumberFormatException e) {
            // 如果时间戳解析失败，返回 false
            return false;
        }
    }
}
