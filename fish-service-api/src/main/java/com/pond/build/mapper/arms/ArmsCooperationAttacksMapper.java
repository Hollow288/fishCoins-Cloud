package com.pond.build.mapper.arms;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.arms.ArmsCharacteristics;
import com.pond.build.model.arms.ArmsCooperationAttacks;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface ArmsCooperationAttacksMapper extends BaseMapper<ArmsCooperationAttacks> {
    int insertBatchSomeColumn(Collection<ArmsCooperationAttacks> entityList);
}
