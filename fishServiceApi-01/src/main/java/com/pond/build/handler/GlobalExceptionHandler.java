package com.pond.build.handler;

import com.alibaba.fastjson2.JSON;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.CommonResult;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public void handleAuthenticationException(HttpServletResponse response, AuthenticationCredentialsNotFoundException ex) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);  // 返回401
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        CommonResult result = new CommonResult<>(HttpStatusCode.ACCESS_TOKEN_ERR.getCode(), HttpStatusCode.ACCESS_TOKEN_ERR.getCnMessage());
        response.getWriter().write(JSON.toJSONString(result));
    }

    // 处理403权限不足异常
    @ExceptionHandler(AccessDeniedException.class)
    public void handleAccessDeniedException(HttpServletResponse response, AccessDeniedException ex) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);  // 返回403
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        CommonResult result = new CommonResult<>(HttpStatusCode.FORBIDDEN_ROLE_ERR.getCode(), HttpStatusCode.FORBIDDEN_ROLE_ERR.getCnMessage());
        response.getWriter().write(JSON.toJSONString(result));
    }

    @ExceptionHandler(Exception.class)
    public void handleException(HttpServletResponse response,Exception ex) throws IOException {
        // 自定义返回内容
        CommonResult result = new CommonResult<>(HttpStatusCode.REQUEST_SERVER_ERROR.getCode(),HttpStatusCode.REQUEST_SERVER_ERROR.getCnMessage());
        // 设置响应内容
        response.setStatus(HttpServletResponse.SC_OK);  // 返回状态码200
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(JSON.toJSONString(result));  // 将 Map 转为 JSON 并写入响应
    }
}
