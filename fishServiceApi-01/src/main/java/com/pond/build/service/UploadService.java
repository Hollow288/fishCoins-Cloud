package com.pond.build.service;

import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UploadService {
    CommonResult<Map<String, Object>> uploadArmsImg(MultipartFile[] file, String armsId, TokenUser user);
}
