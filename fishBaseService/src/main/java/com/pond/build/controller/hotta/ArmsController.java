package com.pond.build.controller.hotta;

import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.CommonResult;
import com.pond.build.remote.hotta.arms.ArmsRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ArmsController {

    @Autowired
    private ArmsRemote armsRemote;

    @PostMapping("/add-arms")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> addArms(@RequestBody Object arms){
        return armsRemote.addArms(arms);
    }


    @GetMapping("/all-arms")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> allArms(@RequestParam(value = "page") Integer page,
                                      @RequestParam(value = "pageSize") Integer pageSize,
                                      @RequestParam(value = "attributeType", defaultValue = "") String attributeType){
        return armsRemote.allArms(page,pageSize,attributeType);
    }

}
