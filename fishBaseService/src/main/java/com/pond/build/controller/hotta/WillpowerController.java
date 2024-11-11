package com.pond.build.controller.hotta;


import com.pond.build.remote.hotta.willpower.WillpowerRemote;
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
    private WillpowerRemote willpowerRemote;

    @GetMapping("/page-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> willpowerByPage(@RequestParam(value = "page") Integer page,
                                                       @RequestParam(value = "page_size") Integer pageSize,
                                                       @RequestParam(value = "search_name", defaultValue = "") String searchName){
        return willpowerRemote.willpowerByPage(page,pageSize,searchName);
    }
}
