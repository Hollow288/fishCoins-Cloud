package com.pond.build.controller.hotta;

import com.pond.build.remote.hotta.nuoCoins.NuoCoinsRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/nuo-coins")
public class NuoCoinsController {

    @Autowired
    private NuoCoinsRemote nuoCoinsRemote;


    @GetMapping("/page-nuo-coins-weekly")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> nuoCoinsWeeklyByPage(@RequestParam(value = "page") Integer page,
                                              @RequestParam(value = "page_size") Integer pageSize){
        return nuoCoinsRemote.nuoCoinsWeeklyByPage(page,pageSize);
    }


    @GetMapping("/nuo-coins-type")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> nuoCoinsTaskTypeInfo(){
        return nuoCoinsRemote.nuoCoinsTaskTypeInfo();
    }


    @PostMapping("/add-edit-nuo-coins-weekly")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> addEditNuoCoinsWeekly(@RequestBody Object nuoCoinsWeekly){
        return nuoCoinsRemote.addEditNuoCoinsWeekly(nuoCoinsWeekly);
    }


    @GetMapping("/id-nuo-coins-weekly/{task_weekly_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> nuoCoinsWeeklyById(@PathVariable(value = "task_weekly_id") Integer taskWeeklyId){
        return nuoCoinsRemote.nuoCoinsWeeklyById(taskWeeklyId);
    }

    @PutMapping("/delete-nuo-coins-weekly")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> deleteNuoCoinsWeekly(@RequestBody Object taskWeeklyIds){
        return nuoCoinsRemote.deleteNuoCoinsWeekly(taskWeeklyIds);
    }
}
