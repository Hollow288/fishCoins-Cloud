package com.pond.build.controller.hotta;

import com.pond.build.model.CommonResult;
import com.pond.build.model.nuoCoins.NuoCoinsTaskType;
import com.pond.build.model.nuoCoins.NuoCoinsTaskWeekly;
import com.pond.build.service.NuoCoinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/nuo-coins")
public class NuoCoinsController {

    @Autowired
    private NuoCoinsService nuoCoinsService;

    @GetMapping("/page-nuo-coins-weekly")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Map<String,Object>> nuoCoinsWeeklyByPage(@RequestParam(value = "page") Integer page,
                                                          @RequestParam(value = "page_size") Integer pageSize){
        return nuoCoinsService.nuoCoinsWeeklyByPage(page,pageSize);
    }


    @GetMapping("/nuo-coins-type")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<List<NuoCoinsTaskType>> nuoCoinsTaskTypeInfo(){
        return nuoCoinsService.nuoCoinsTaskTypeInfo();
    }


    @PostMapping("/add-edit-nuo-coins-weekly")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<NuoCoinsTaskWeekly> addEditNuoCoinsWeekly(@RequestBody NuoCoinsTaskWeekly nuoCoinsTaskWeekly){
        return nuoCoinsService.addEditNuoCoinsWeekly(nuoCoinsTaskWeekly);
    }


    @GetMapping("/id-nuo-coins-weekly/{task_weekly_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<NuoCoinsTaskWeekly> nuoCoinsWeeklyById(@PathVariable(value = "task_weekly_id") Integer taskWeeklyId){
        return nuoCoinsService.nuoCoinsWeeklyById(taskWeeklyId);
    }

    @PutMapping("/delete-nuo-coins-weekly")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Object> deleteNuoCoinsWeekly(@RequestBody Map<String,Object> taskWeeklyIds){
        return nuoCoinsService.deleteNuoCoinsWeekly(taskWeeklyIds);
    }
}
