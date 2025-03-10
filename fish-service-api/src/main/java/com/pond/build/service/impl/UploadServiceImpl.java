package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ctrip.framework.apollo.spring.property.SpringValue;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.AttachmentInformationMapper;
import com.pond.build.mapper.EventConsultationMapper;
import com.pond.build.mapper.arms.ArmsMapper;
import com.pond.build.mapper.food.FoodMapper;
import com.pond.build.mapper.willpower.WillpowerMapper;
import com.pond.build.model.AttachmentInformation;
import com.pond.build.model.CommonResult;
import com.pond.build.model.EventConsultation;
import com.pond.build.model.TokenUser;
import com.pond.build.model.arms.Arms;
import com.pond.build.model.food.Food;
import com.pond.build.model.willpower.Willpower;
import com.pond.build.service.UploadService;
import com.pond.build.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UploadServiceImpl implements UploadService {

    @Value(value = "${minio.endpoint:}")
    private String address;

    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private AttachmentInformationMapper attachmentInformationMapper;

    @Autowired
    private ArmsMapper armsMapper;

    @Autowired
    private WillpowerMapper willpowerMapper;

    @Autowired
    private EventConsultationMapper eventConsultationMapper;

    @Autowired
    private FoodMapper foodMapper;

    @Override
    public CommonResult<Map<String, Object>> uploadArmsImg(MultipartFile[] files, String armsId, TokenUser user) {

        String bucketName = "fishcoins-arms-img";
        String filePath = "/"+ LocalDate.now() + "/";

        minioUtil.existBucket(bucketName);
        //上传文件到Minio
        List<String> uploadNames = minioUtil.upload(files, bucketName, filePath);

        List<String> resultNames = uploadNames.stream().map(m -> address + "/" + bucketName + filePath + m).toList();


        for (String resultName : resultNames) {
            AttachmentInformation attachmentInformation = new AttachmentInformation();
            attachmentInformation.setCreateBy(String.valueOf(user.getUserId()));
            attachmentInformation.setOriTableId(armsId);
            attachmentInformation.setOriTableName("arms");
            attachmentInformation.setAttachUrl(resultName);
            attachmentInformationMapper.insert(attachmentInformation);


            UpdateWrapper<Arms> userUpdateWrapper = new UpdateWrapper<>();
            userUpdateWrapper.eq("arms_id",armsId);
            userUpdateWrapper.set("arms_thumbnail_url",resultName);
            userUpdateWrapper.set("update_by",user.getUserId());
            armsMapper.update(null,userUpdateWrapper);
        }

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功");
    }



    @Override
    public CommonResult<Map<String, Object>> uploadWillpowerImg(MultipartFile[] files, String willpowerId, TokenUser user) {

        String bucketName = "fishcoins-willpower-img";
        String filePath = "/"+ LocalDate.now() + "/";

        minioUtil.existBucket(bucketName);
        //上传文件到Minio
        List<String> uploadNames = minioUtil.upload(files, bucketName, filePath);

        List<String> resultNames = uploadNames.stream().map(m -> address + "/" + bucketName + filePath + m).toList();


        for (String resultName : resultNames) {
            AttachmentInformation attachmentInformation = new AttachmentInformation();
            attachmentInformation.setCreateBy(String.valueOf(user.getUserId()));
            attachmentInformation.setOriTableId(willpowerId);
            attachmentInformation.setOriTableName("willpower");
            attachmentInformation.setAttachUrl(resultName);
            attachmentInformationMapper.insert(attachmentInformation);


            UpdateWrapper<Willpower> userUpdateWrapper = new UpdateWrapper<>();
            userUpdateWrapper.eq("willpower_id",willpowerId);
            userUpdateWrapper.set("willpower_thumbnail_url",resultName);
            userUpdateWrapper.set("update_by",user.getUserId());
            willpowerMapper.update(null,userUpdateWrapper);
        }

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public CommonResult<Map<String, Object>> uploadEventConsultationImg(MultipartFile[] files, String consultationId, TokenUser user) {
        String bucketName = "fishcoins-event-consultation-img";
        String filePath = "/"+ LocalDate.now() + "/";

        minioUtil.existBucket(bucketName);
        //上传文件到Minio
        List<String> uploadNames = minioUtil.upload(files, bucketName, filePath);

        List<String> resultNames = uploadNames.stream().map(m -> address + "/" + bucketName + filePath + m).toList();


        for (String resultName : resultNames) {
            AttachmentInformation attachmentInformation = new AttachmentInformation();
            attachmentInformation.setCreateBy(String.valueOf(user.getUserId()));
            attachmentInformation.setOriTableId(consultationId);
            attachmentInformation.setOriTableName("event_consultation");
            attachmentInformation.setAttachUrl(resultName);
            attachmentInformationMapper.insert(attachmentInformation);


            UpdateWrapper<EventConsultation> userUpdateWrapper = new UpdateWrapper<>();
            userUpdateWrapper.eq("consultation_id",consultationId);
            userUpdateWrapper.set("consultation_thumbnail_url",resultName);
            eventConsultationMapper.update(null,userUpdateWrapper);
        }

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功");
    }



    @Override
    public CommonResult<Map<String, Object>> uploadFoodImg(MultipartFile[] files, String foodId, TokenUser user) {
        String bucketName = "fishcoins-food-img";
        String filePath = "/"+ LocalDate.now() + "/";

        minioUtil.existBucket(bucketName);
        //上传文件到Minio
        List<String> uploadNames = minioUtil.upload(files, bucketName, filePath);

        List<String> resultNames = uploadNames.stream().map(m -> address + "/" + bucketName + filePath + m).toList();


        for (String resultName : resultNames) {
            AttachmentInformation attachmentInformation = new AttachmentInformation();
            attachmentInformation.setCreateBy(String.valueOf(user.getUserId()));
            attachmentInformation.setOriTableId(foodId);
            attachmentInformation.setOriTableName("food");
            attachmentInformation.setAttachUrl(resultName);
            attachmentInformationMapper.insert(attachmentInformation);


            UpdateWrapper<Food> userUpdateWrapper = new UpdateWrapper<>();
            userUpdateWrapper.eq("food_id",foodId);
            userUpdateWrapper.set("food_thumbnail_url",resultName);
            foodMapper.update(null,userUpdateWrapper);
        }

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功");
    }
}
