package com.pond.build.controller.hotta;

import com.pond.build.aop.InjectUserDetails;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.mimicry.Mimicry;
import com.pond.build.model.yuCoins.YuCoinsTaskType;
import com.pond.build.model.yuCoins.YuCoinsTaskWeekly;
import com.pond.build.service.YuCoinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/yu-coins")
public class YuCoinsController {

    @Autowired
    private YuCoinsService yuCoinsService;

    @GetMapping("/page-yu-coins-weekly")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Map<String,Object>> yuCoinsWeeklyByPage(@RequestParam(value = "page") Integer page,
                                                          @RequestParam(value = "page_size") Integer pageSize){
        return yuCoinsService.yuCoinsWeeklyByPage(page,pageSize);
    }


    @GetMapping("/yu-coins-type")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<List<YuCoinsTaskType>> yuCoinsTaskTypeInfo(){
        return yuCoinsService.yuCoinsTaskTypeInfo();
    }


    @PostMapping("/add-edit-yu-coins-weekly")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<YuCoinsTaskWeekly> addEditYuCoinsWeekly(@RequestBody YuCoinsTaskWeekly yuCoinsTaskWeekly){
        return yuCoinsService.addEditYuCoinsWeekly(yuCoinsTaskWeekly);
    }


    @GetMapping("/id-yu-coins-weekly/{task_weekly_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<YuCoinsTaskWeekly> yuCoinsWeeklyById(@PathVariable(value = "task_weekly_id") Integer taskWeeklyId){
        return yuCoinsService.yuCoinsWeeklyById(taskWeeklyId);
    }

    @PutMapping("/delete-yu-coins-weekly")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Object> deleteYuCoinsWeekly(@RequestBody Map<String,Object> taskWeeklyIds){
        return yuCoinsService.deleteYuCoinsWeekly(taskWeeklyIds);
    }
}
