package com.pond.build.mapper.arms;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.arms.ArmsCooperationAttacks;
import com.pond.build.model.arms.ArmsDodgeAttacks;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface ArmsDodgeAttacksMapper  extends BaseMapper<ArmsDodgeAttacks> {

    int insertBatchSomeColumn(Collection<ArmsDodgeAttacks> entityList);
}
