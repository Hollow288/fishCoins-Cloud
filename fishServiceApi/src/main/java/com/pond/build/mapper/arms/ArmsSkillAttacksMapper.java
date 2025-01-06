package com.pond.build.mapper.arms;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.arms.ArmsPrimaryAttacks;
import com.pond.build.model.arms.ArmsSkillAttacks;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface ArmsSkillAttacksMapper  extends BaseMapper<ArmsSkillAttacks> {

    int insertBatchSomeColumn(Collection<ArmsSkillAttacks> entityList);
}
