package com.pond.build.aop;

import com.pond.build.model.TokenUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

@Aspect
@Component
public class UserDetailsInjectAspect {

    @Around("@annotation(InjectUserDetails)")
    public Object injectUsername(ProceedingJoinPoint joinPoint) throws Throwable {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        TokenUser tokenUser = new TokenUser();
        tokenUser.setUserId(Long.valueOf(userDetails.getUsername()));
        tokenUser.setPermissions(userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList());

        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();


        Parameter[] parameters = method.getParameters();

        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i].getType() == TokenUser.class) {
                args[i] = tokenUser;
                break;
            }
        }

        return joinPoint.proceed(args);
    }
}
