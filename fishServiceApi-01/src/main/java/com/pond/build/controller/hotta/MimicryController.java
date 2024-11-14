package com.pond.build.controller.hotta;

import com.pond.build.aop.InjectUserDetails;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.mimicry.Mimicry;
import com.pond.build.model.willpower.Willpower;
import com.pond.build.service.MimicryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mimicry")
public class MimicryController {

    @Autowired
    private MimicryService mimicryService;

    @GetMapping("/page-mimicry")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Map<String,Object>> mimicryByPage(@RequestParam(value = "page") Integer page,
                                                            @RequestParam(value = "page_size") Integer pageSize,
                                                            @RequestParam(value = "search_name", defaultValue = "") String searchName){
        return mimicryService.mimicryByPage(page,pageSize,searchName);
    }


    @PostMapping("/add-mimicry")
    @PreAuthorize("hasRole('ADMIN')")
    @InjectUserDetails
    public CommonResult<Mimicry> addMimicry(@RequestBody Mimicry mimicry, TokenUser user){
        return mimicryService.addMimicry(mimicry,user);
    }


    @GetMapping("/id-mimicry/{mimicry_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Mimicry> mimicryById(@PathVariable(value = "mimicry_id") Integer mimicryId){
        return mimicryService.mimicryById(mimicryId);
    }
}
