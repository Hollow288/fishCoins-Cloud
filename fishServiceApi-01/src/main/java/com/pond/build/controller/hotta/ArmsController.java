package com.pond.build.controller.hotta;

import com.pond.build.aop.InjectUserDetails;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.arms.Arms;
import com.pond.build.service.ArmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ArmsController {

    @Autowired
    private ArmsService armsService;

    @PostMapping("/add-arms")
    @PreAuthorize("hasRole('ADMIN')")
    @InjectUserDetails
    public CommonResult<Arms> addArms(@RequestBody Arms arms, TokenUser user){
        return armsService.addArms(arms,user);
    }


    @GetMapping("/all-arms")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<List<Arms>> allArms(@RequestParam(value = "page") Integer page,
                                            @RequestParam(value = "pageSize") Integer pageSize,
                                            @RequestParam(value = "attributeType", defaultValue = "") String attributeType){
        return armsService.allArms(page,pageSize,attributeType);
    }

}
