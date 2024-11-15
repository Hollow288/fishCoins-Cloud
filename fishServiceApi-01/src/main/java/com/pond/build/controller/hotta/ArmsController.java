package com.pond.build.controller.hotta;

import com.pond.build.aop.InjectUserDetails;
import com.pond.build.model.ArmsMimicryWillpower;
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
@RequestMapping("/arms")
public class ArmsController {

    @Autowired
    private ArmsService armsService;

    @PostMapping("/add-arms")
    @PreAuthorize("hasRole('ADMIN')")
    @InjectUserDetails
    public CommonResult<Arms> addArms(@RequestBody Arms arms, TokenUser user){
        return armsService.addArms(arms,user);
    }

    @PutMapping("/edit-arms")
    @PreAuthorize("hasRole('ADMIN')")
    @InjectUserDetails
    public CommonResult<Arms> editArms(@RequestBody Arms arms, TokenUser user){
        return armsService.editArms(arms,user);
    }


    @PutMapping("/delete-arms")
    @PreAuthorize("hasRole('ADMIN')")
    @InjectUserDetails
    public CommonResult<Object> deleteArms(@RequestBody Map<String,Object> armsIds, TokenUser user){
        return armsService.deleteArms(armsIds,user);
    }


    @GetMapping("/page-arms")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Map<String,Object>> armsByPage(@RequestParam(value = "page") Integer page,
                                            @RequestParam(value = "page_size") Integer pageSize,
                                            @RequestParam(value = "attribute_type", defaultValue = "") String attributeType){
        return armsService.armsByPage(page,pageSize,attributeType);
    }


    @GetMapping("/id-arms/{arms_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Arms> armsById(@PathVariable(value = "arms_id") Integer armsId){
        return armsService.armsById(armsId);
    }


    @GetMapping("/arms-mimicry-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Map<String,Object>> armsMimicryWillpower(){
        return armsService.armsMimicryWillpower();
    }



    @GetMapping("/bind/{arms_id}/arms-mimicry-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<ArmsMimicryWillpower> armsMimicryWillpowerBindInfo(@PathVariable(value = "arms_id") Integer armsId){
        return armsService.armsMimicryWillpowerBindInfo(armsId);
    }


    @PutMapping("/edit-arms-mimicry-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Object> editArmsMimicryWillpower(@RequestBody ArmsMimicryWillpower armsMimicryWillpower){
        return armsService.editArmsMimicryWillpower(armsMimicryWillpower);
    }

}
