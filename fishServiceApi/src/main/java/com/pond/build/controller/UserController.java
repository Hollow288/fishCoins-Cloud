package com.pond.build.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/hello/{name}")
//    @PreAuthorize("hasRole('ADMIN')")
    public String test(@PathVariable(value = "name") String name){

//        try {
//            Thread.sleep(4000); // 暂停 4 秒
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt(); // 恢复中断状态
//            throw new RuntimeException("线程被中断", e);
//        }

        if(name.equals("namee")){
            throw new RuntimeException("id不能为负数");
        }
        return "hello "+name+"，this is first messge--01";
    }

}
