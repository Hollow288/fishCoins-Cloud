package com.pond.build.controller.minio;


import com.pond.build.aop.InjectUserDetails;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping("/{arms_id}/arms-img")
    @PreAuthorize("hasRole('ADMIN')")
    @InjectUserDetails
    public CommonResult<Map<String,Object>> uploadArmsImg(@RequestPart("file") MultipartFile[] file, @PathVariable("arms_id") String armsId, TokenUser user) {
        return uploadService.uploadArmsImg(file, armsId, user);
    }

    @PostMapping("/{willpower_id}/willpower-img")
    @PreAuthorize("hasRole('ADMIN')")
    @InjectUserDetails
    public CommonResult<Map<String,Object>> uploadWillpowerImg(@RequestPart("file") MultipartFile[] file, @PathVariable("willpower_id") String willpowerId, TokenUser user) {
        return uploadService.uploadWillpowerImg(file, willpowerId, user);
    }



}
