package com.pond.build.mapper.arms;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.arms.ArmsDodgeAttacks;
import com.pond.build.model.arms.ArmsExclusives;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface ArmsExclusivesMapper extends BaseMapper<ArmsExclusives> {

    int insertBatchSomeColumn(Collection<ArmsExclusives> entityList);
}
