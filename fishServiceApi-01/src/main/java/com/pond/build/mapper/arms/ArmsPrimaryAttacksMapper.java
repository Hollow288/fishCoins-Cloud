package com.pond.build.mapper.arms;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.arms.Arms;
import com.pond.build.model.arms.ArmsPrimaryAttacks;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface ArmsPrimaryAttacksMapper  extends BaseMapper<ArmsPrimaryAttacks> {

    int insertBatchSomeColumn(Collection<ArmsPrimaryAttacks> entityList);
}
