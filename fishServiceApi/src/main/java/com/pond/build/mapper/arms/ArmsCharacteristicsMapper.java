package com.pond.build.mapper.arms;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.arms.ArmsCharacteristics;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface ArmsCharacteristicsMapper  extends BaseMapper<ArmsCharacteristics> {
    int insertBatchSomeColumn(Collection<ArmsCharacteristics> entityList);
}
