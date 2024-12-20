package com.pond.build.controller.hotta;

import com.pond.build.enums.HttpStatusCode;
import com.pond.build.model.CommonResult;
import com.pond.build.remote.hotta.arms.ArmsRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/arms")
public class ArmsController {


    @Autowired
    private ArmsRemote armsRemote;

    @PostMapping("/add-arms")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> addArms(@RequestBody Object arms){
        return armsRemote.addArms(arms);
    }


    @PutMapping("/edit-arms")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> editArms(@RequestBody Object arms){
        return armsRemote.editArms(arms);
    }


    @PutMapping("/delete-arms")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> deleteArms(@RequestBody Object armsIds){
        return armsRemote.deleteArms(armsIds);
    }


    @GetMapping("/page-arms")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> armsByPage(@RequestParam(value = "page") Integer page,
                                      @RequestParam(value = "page_size") Integer pageSize,
                                      @RequestParam(value = "attribute_type", defaultValue = "") String attributeType){
        return armsRemote.armsByPage(page,pageSize,attributeType);
    }


    @GetMapping("/id-arms/{arms_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> armsById(@PathVariable(value = "arms_id") Integer armsId){
        return armsRemote.armsById(armsId);
    }


    @GetMapping("/arms-mimicry-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> armsMimicryWillpower(){
        return armsRemote.armsMimicryWillpower();
    }

    @GetMapping("/bind/{arms_id}/arms-mimicry-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> armsMimicryWillpowerBindInfo(@PathVariable(value = "arms_id") Integer armsId){
        return armsRemote.armsMimicryWillpowerBindInfo(armsId);
    }


    @PutMapping("/edit-arms-mimicry-willpower")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> editArmsMimicryWillpower(@RequestBody Object armsMimicryWillpower){
        return armsRemote.editArmsMimicryWillpower(armsMimicryWillpower);
    }

}
