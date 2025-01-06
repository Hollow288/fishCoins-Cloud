package com.pond.build.controller.hotta;

import com.pond.build.remote.hotta.willpower.WillpowerRemote;
import com.pond.build.remote.hotta.yuCoins.YuCoinsRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/yu-coins")
public class YuCoinsController {

    @Autowired
    private YuCoinsRemote yuCoinsRemote;


    @GetMapping("/page-yu-coins-weekly")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> yuCoinsWeeklyByPage(@RequestParam(value = "page") Integer page,
                                              @RequestParam(value = "page_size") Integer pageSize){
        return yuCoinsRemote.yuCoinsWeeklyByPage(page,pageSize);
    }


    @GetMapping("/yu-coins-type")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> yuCoinsTaskTypeInfo(){
        return yuCoinsRemote.yuCoinsTaskTypeInfo();
    }


    @PostMapping("/add-edit-yu-coins-weekly")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> addEditYuCoinsWeekly(@RequestBody Object yuCoinsWeekly){
        return yuCoinsRemote.addEditYuCoinsWeekly(yuCoinsWeekly);
    }


    @GetMapping("/id-yu-coins-weekly/{task_weekly_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> yuCoinsWeeklyById(@PathVariable(value = "task_weekly_id") Integer taskWeeklyId){
        return yuCoinsRemote.yuCoinsWeeklyById(taskWeeklyId);
    }

    @PutMapping("/delete-yu-coins-weekly")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> deleteYuCoinsWeekly(@RequestBody Object taskWeeklyIds){
        return yuCoinsRemote.deleteYuCoinsWeekly(taskWeeklyIds);
    }
}
