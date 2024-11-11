package com.pond.build.controller.hotta;

import com.pond.build.model.CommonResult;
import com.pond.build.service.WillpowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/willpower")
public class WillpowerController {

    @Autowired
    private WillpowerService willpowerService;

    @GetMapping("/page-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Map<String,Object>> willpowerByPage(@RequestParam(value = "page") Integer page,
                                                       @RequestParam(value = "page_size") Integer pageSize,
                                                       @RequestParam(value = "search_name", defaultValue = "") String searchName){
        return willpowerService.willpowerByPage(page,pageSize,searchName);
    }
}
