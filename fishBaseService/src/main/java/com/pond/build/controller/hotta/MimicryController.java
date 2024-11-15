package com.pond.build.controller.hotta;

import com.pond.build.remote.hotta.mimicry.MimicryRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mimicry")
public class MimicryController {

    @Autowired
    private MimicryRemote mimicryRemote;


    @GetMapping("/page-mimicry")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> mimicryByPage(@RequestParam(value = "page") Integer page,
                                              @RequestParam(value = "page_size") Integer pageSize,
                                              @RequestParam(value = "search_name", defaultValue = "") String searchName){
        return mimicryRemote.mimicryByPage(page,pageSize,searchName);
    }


    @PostMapping("/add-mimicry")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> addMimicry(@RequestBody Object mimicry){
        return mimicryRemote.addMimicry(mimicry);
    }

    @GetMapping("/id-mimicry/{mimicry_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> mimicryById(@PathVariable(value = "mimicry_id") Integer mimicryId){
        return mimicryRemote.mimicryById(mimicryId);
    }


    @PutMapping("/edit-mimicry")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> editMimicry(@RequestBody Object mimicry){
        return mimicryRemote.editMimicry(mimicry);
    }


    @PutMapping("/delete-mimicry")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> deleteMimicry(@RequestBody Object mimicryIds){
        return mimicryRemote.deleteMimicry(mimicryIds);
    }
}
