package com.pond.build.service.impl;

import com.pond.build.aop.InjectUserDetails;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.arms.*;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.arms.Arms;
import com.pond.build.model.arms.ArmsCooperationAttacks;
import com.pond.build.service.ArmsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ArmsServiceImpl implements ArmsService {

    @Autowired
    private ArmsMapper armsMapper;

    @Autowired
    private ArmsCharacteristicsMapper armsCharacteristicsMapper;

    @Autowired
    private ArmsCooperationAttacksMapper armsCooperationAttacksMapper;

    @Autowired
    private ArmsDodgeAttacksMapper armsDodgeAttacksMapper;

    @Autowired
    private ArmsExclusivesMapper armsExclusivesMapper;

    @Autowired
    private ArmsPrimaryAttacksMapper armsPrimaryAttacksMapper;

    @Autowired
    private ArmsSkillAttacksMapper armsSkillAttacksMapper;

    @Autowired
    private ArmsStarRatingsMapper armsStarRatingsMapper;

    @Override
    public CommonResult<Arms> addArms(Arms arms, TokenUser user) {
        arms.setCreateBy(String.valueOf(user.getUserId()));
        armsMapper.insert(arms);
        // 武器特质
        arms.getArmsCharacteristics().forEach(armsCharacteristics -> armsCharacteristics.setArmsId(arms.getArmsId()));
        armsCharacteristicsMapper.insertBatchSomeColumn(arms.getArmsCharacteristics());
        // 武器联携
        arms.getArmsCooperationAttacks().forEach(armsCooperationAttacks -> armsCooperationAttacks.setArmsId(arms.getArmsId()));
        armsCooperationAttacksMapper.insertBatchSomeColumn(arms.getArmsCooperationAttacks());
        // 武器闪攻
        arms.getArmsDodgeAttacks().forEach(armsDodgeAttacks -> armsDodgeAttacks.setArmsId(arms.getArmsId()));
        armsDodgeAttacksMapper.insertBatchSomeColumn(arms.getArmsDodgeAttacks());
        // 武器专属
        arms.getArmsExclusives().forEach(armsExclusives -> armsExclusives.setArmsId(arms.getArmsId()));
        armsExclusivesMapper.insertBatchSomeColumn(arms.getArmsExclusives());
        // 武器普攻
        arms.getArmsPrimaryAttacks().forEach(armsPrimaryAttacks -> armsPrimaryAttacks.setArmsId(arms.getArmsId()));
        armsPrimaryAttacksMapper.insertBatchSomeColumn(arms.getArmsPrimaryAttacks());
        // 武器技能
        arms.getArmsSkillAttacks().forEach(armsSkillAttacks -> armsSkillAttacks.setArmsId(arms.getArmsId()));
        armsSkillAttacksMapper.insertBatchSomeColumn(arms.getArmsSkillAttacks());
        // 武器星级
        arms.getArmsStarRatings().forEach(armsStarRatings -> armsStarRatings.setArmsId(arms.getArmsId()));
        armsStarRatingsMapper.insertBatchSomeColumn(arms.getArmsStarRatings());
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功",arms);
    }

    @Override
    public CommonResult<List<Arms>> allArms(Integer page, Integer pageSize, String attributeType) {
        int offset = (page - 1) * pageSize;
        int limit = pageSize;
        List<String> paramsList = new ArrayList<>();
        if(StringUtils.isNotBlank(attributeType)){
            paramsList = List.of(attributeType.split(","));
        }
        List<Arms> allArmsInfoByPage = armsMapper.getAllArmsInfoByPage(offset,limit,paramsList);
        System.out.println(allArmsInfoByPage);
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",allArmsInfoByPage);
    }
}
