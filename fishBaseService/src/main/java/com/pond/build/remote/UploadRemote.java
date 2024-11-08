package com.pond.build.remote;

import com.pond.build.config.FeignClientConfig;
import com.pond.build.model.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@FeignClient(name= "fishServiceApi", contextId = "uploadServiceClient")
public interface UploadRemote {

    @PostMapping(value = "/upload/{arms_id}/arms-img",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    Map<String,Object> uploadArmsImg(@RequestPart(value = "file") MultipartFile[] file, @PathVariable("arms_id") String armsId);
}
