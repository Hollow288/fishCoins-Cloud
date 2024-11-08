package com.pond.build.controller.minio;



import com.pond.build.model.CommonResult;
import com.pond.build.remote.UploadRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


@RestController
public class UploadController {

    @Autowired
    private UploadRemote uploadRemote;

    @PostMapping(value = "/upload/{arms_id}/arms-img")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> uploadArmsImg(@RequestPart("file") MultipartFile[] file, @PathVariable("arms_id") String armsId) {
        return uploadRemote.uploadArmsImg(file, armsId);

    }



}
