package com.pond.build.service;

import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UploadService {
    CommonResult<Map<String, Object>> uploadArmsImg(MultipartFile[] file, String armsId, TokenUser user);

    CommonResult<Map<String, Object>> uploadWillpowerImg(MultipartFile[] files, String willpowerId, TokenUser user);

    CommonResult<Map<String, Object>> uploadEventConsultationImg(MultipartFile[] files, String consultationId, TokenUser user);

    CommonResult<Map<String, Object>> uploadFoodImg(MultipartFile[] files, String foodId, TokenUser user);
}
