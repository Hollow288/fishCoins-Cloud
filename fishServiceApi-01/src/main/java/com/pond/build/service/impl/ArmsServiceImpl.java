package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.pond.build.aop.InjectUserDetails;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.arms.*;
import com.pond.build.model.CommonResult;
import com.pond.build.model.TokenUser;
import com.pond.build.model.arms.Arms;
import com.pond.build.model.arms.ArmsCooperationAttacks;
import com.pond.build.model.arms.response.ArmsBasic;
import com.pond.build.service.ArmsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

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
        if (!CollectionUtils.isEmpty(arms.getArmsCharacteristics()))armsCharacteristicsMapper.insertBatchSomeColumn(arms.getArmsCharacteristics());
        // 武器联携
        arms.getArmsCooperationAttacks().forEach(armsCooperationAttacks -> armsCooperationAttacks.setArmsId(arms.getArmsId()));
        if (!CollectionUtils.isEmpty(arms.getArmsCooperationAttacks()))armsCooperationAttacksMapper.insertBatchSomeColumn(arms.getArmsCooperationAttacks());
        // 武器闪攻
        arms.getArmsDodgeAttacks().forEach(armsDodgeAttacks -> armsDodgeAttacks.setArmsId(arms.getArmsId()));
        if (!CollectionUtils.isEmpty(arms.getArmsDodgeAttacks()))armsDodgeAttacksMapper.insertBatchSomeColumn(arms.getArmsDodgeAttacks());
        // 武器专属
        arms.getArmsExclusives().forEach(armsExclusives -> armsExclusives.setArmsId(arms.getArmsId()));
        if (!CollectionUtils.isEmpty(arms.getArmsExclusives()))armsExclusivesMapper.insertBatchSomeColumn(arms.getArmsExclusives());
        // 武器普攻
        arms.getArmsPrimaryAttacks().forEach(armsPrimaryAttacks -> armsPrimaryAttacks.setArmsId(arms.getArmsId()));
        if (!CollectionUtils.isEmpty(arms.getArmsPrimaryAttacks()))armsPrimaryAttacksMapper.insertBatchSomeColumn(arms.getArmsPrimaryAttacks());
        // 武器技能
        arms.getArmsSkillAttacks().forEach(armsSkillAttacks -> armsSkillAttacks.setArmsId(arms.getArmsId()));
        if (!CollectionUtils.isEmpty(arms.getArmsSkillAttacks()))armsSkillAttacksMapper.insertBatchSomeColumn(arms.getArmsSkillAttacks());
        // 武器星级
        arms.getArmsStarRatings().forEach(armsStarRatings -> armsStarRatings.setArmsId(arms.getArmsId()));
        if (!CollectionUtils.isEmpty(arms.getArmsStarRatings()))armsStarRatingsMapper.insertBatchSomeColumn(arms.getArmsStarRatings());
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功",arms);
    }

    @Override
    public CommonResult<Arms> editArms(Arms arms, TokenUser user) {
        arms.setUpdateBy(String.valueOf(user.getUserId()));
        armsMapper.updateById(arms);

        armsMapper.deleteDetailByArmsId(arms.getArmsId());

        // 武器特质
        arms.getArmsCharacteristics().forEach(armsCharacteristics -> armsCharacteristics.setArmsId(arms.getArmsId()));
        if (!CollectionUtils.isEmpty(arms.getArmsCharacteristics()))armsCharacteristicsMapper.insertBatchSomeColumn(arms.getArmsCharacteristics());
        // 武器联携
        arms.getArmsCooperationAttacks().forEach(armsCooperationAttacks -> armsCooperationAttacks.setArmsId(arms.getArmsId()));
        if (!CollectionUtils.isEmpty(arms.getArmsCooperationAttacks()))armsCooperationAttacksMapper.insertBatchSomeColumn(arms.getArmsCooperationAttacks());
        // 武器闪攻
        arms.getArmsDodgeAttacks().forEach(armsDodgeAttacks -> armsDodgeAttacks.setArmsId(arms.getArmsId()));
        if (!CollectionUtils.isEmpty(arms.getArmsDodgeAttacks()))armsDodgeAttacksMapper.insertBatchSomeColumn(arms.getArmsDodgeAttacks());
        // 武器专属
        arms.getArmsExclusives().forEach(armsExclusives -> armsExclusives.setArmsId(arms.getArmsId()));
        if (!CollectionUtils.isEmpty(arms.getArmsExclusives()))armsExclusivesMapper.insertBatchSomeColumn(arms.getArmsExclusives());
        // 武器普攻
        arms.getArmsPrimaryAttacks().forEach(armsPrimaryAttacks -> armsPrimaryAttacks.setArmsId(arms.getArmsId()));
        if (!CollectionUtils.isEmpty(arms.getArmsPrimaryAttacks()))armsPrimaryAttacksMapper.insertBatchSomeColumn(arms.getArmsPrimaryAttacks());
        // 武器技能
        arms.getArmsSkillAttacks().forEach(armsSkillAttacks -> armsSkillAttacks.setArmsId(arms.getArmsId()));
        if (!CollectionUtils.isEmpty(arms.getArmsSkillAttacks()))armsSkillAttacksMapper.insertBatchSomeColumn(arms.getArmsSkillAttacks());
        // 武器星级
        arms.getArmsStarRatings().forEach(armsStarRatings -> armsStarRatings.setArmsId(arms.getArmsId()));
        if (!CollectionUtils.isEmpty(arms.getArmsStarRatings()))armsStarRatingsMapper.insertBatchSomeColumn(arms.getArmsStarRatings());
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功",arms);
    }

    @Override
    public CommonResult<Object> deleteArms(Map<String, Object> armsIds, TokenUser user) {
        List<String> armsIdList = (List<String>)armsIds.get("armsIds");
        LambdaUpdateWrapper<Arms> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(Arms::getArmsId, armsIdList)
                .set(Arms::getDelFlag, "1")
                .set(Arms::getUpdateTime, new Date())
                .set(Arms::getUpdateBy, user.getUserId());

        // 执行批量更新操作
        // 在 update(T entity, Wrapper<T> updateWrapper) 时，entity 不能为空，否则自动填充失效。
        boolean updateResult = armsMapper.update(null, updateWrapper) > 0;

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public CommonResult<Map<String,Object>> armsByPage(Integer page, Integer pageSize, String attributeType) {
        int offset = (page - 1) * pageSize;
        int limit = pageSize;
        List<String> paramsList = new ArrayList<>();
        if(StringUtils.isNotBlank(attributeType)){
            paramsList = List.of(attributeType.split(","));
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", armsMapper.getCountArms(paramsList));
        List<ArmsBasic> allArmsInfoByPage = armsMapper.getArmsBasicInfoByPage(offset,limit,paramsList);
        resultMap.put("data", allArmsInfoByPage);
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);

        System.out.println(allArmsInfoByPage);
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",resultMap);
    }

    @Override
    public CommonResult<Arms> armsById(Integer armsId) {
        Arms arms = armsMapper.getArmsInfoById(armsId);
        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",arms);
    }
}
