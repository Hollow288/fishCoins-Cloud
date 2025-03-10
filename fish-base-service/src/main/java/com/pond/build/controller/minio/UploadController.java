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
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadRemote uploadRemote;

    @PostMapping(value = "/{arms_id}/arms-img")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> uploadArmsImg(@RequestPart("file") MultipartFile[] file, @PathVariable("arms_id") String armsId) {
        return uploadRemote.uploadArmsImg(file, armsId);

    }


    @PostMapping(value = "/{willpower_id}/willpower-img")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> uploadWillpowerImg(@RequestPart("file") MultipartFile[] file, @PathVariable("willpower_id") String willpowerId) {
        return uploadRemote.uploadWillpowerImg(file, willpowerId);
    }


    @PostMapping(value = "/{consultation_id}/event-consultation-img")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> uploadEventConsultationImg(@RequestPart("file") MultipartFile[] file, @PathVariable("consultation_id") String consultationId) {
        return uploadRemote.uploadEventConsultationImg(file, consultationId);
    }


    @PostMapping(value = "/{food_id}/food-img")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> uploadFoodImg(@RequestPart("file") MultipartFile[] file, @PathVariable("food_id") String foodId) {
        return uploadRemote.uploadFoodImg(file, foodId);
    }



}
