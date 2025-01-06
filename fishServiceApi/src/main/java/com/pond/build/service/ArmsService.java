package com.pond.build.service;

import com.pond.build.model.ArmsMimicryWillpower;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.arms.Arms;

import java.util.List;
import java.util.Map;

public interface ArmsService {
    CommonResult<Arms> addArms(Arms arms, TokenUser user);

    CommonResult<Arms> editArms(Arms arms, TokenUser user);

    CommonResult<Object> deleteArms(Map<String,Object> armsIds, TokenUser user);

    CommonResult<Map<String,Object>> armsByPage(Integer page, Integer pageSize, String attributeType);

    CommonResult<Arms> armsById(Integer armsId);

    CommonResult<Map<String, Object>> armsMimicryWillpower();

    CommonResult<ArmsMimicryWillpower> armsMimicryWillpowerBindInfo(Integer armsId);

    CommonResult<Object> editArmsMimicryWillpower(ArmsMimicryWillpower armsMimicryWillpower);
}
