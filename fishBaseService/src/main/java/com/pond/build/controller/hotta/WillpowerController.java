package com.pond.build.controller.hotta;


import com.pond.build.remote.hotta.willpower.WillpowerRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/willpower")
public class WillpowerController {

    @Autowired
    private WillpowerRemote willpowerRemote;

    @PostMapping("/add-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> addWillpower(@RequestBody Object willpower){
        return willpowerRemote.addWillpower(willpower);
    }


    @PutMapping("/edit-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> editWillpower(@RequestBody Object willpower){
        return willpowerRemote.editWillpower(willpower);
    }


    @PutMapping("/delete-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> deleteWillpower(@RequestBody Object willpowerIds){
        return willpowerRemote.deleteWillpower(willpowerIds);
    }


    @GetMapping("/page-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> willpowerByPage(@RequestParam(value = "page") Integer page,
                                                       @RequestParam(value = "page_size") Integer pageSize,
                                                       @RequestParam(value = "search_name", defaultValue = "") String searchName){
        return willpowerRemote.willpowerByPage(page,pageSize,searchName);
    }


    @GetMapping("/id-willpower/{willpower_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> willpowerById(@PathVariable(value = "willpower_id") Integer willpowerId){
        return willpowerRemote.willpowerById(willpowerId);
    }
}
