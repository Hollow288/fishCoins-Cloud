package com.pond.build.mapper.willpower;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.arms.ArmsCharacteristics;
import com.pond.build.model.willpower.WillpowerClassification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface WillpowerClassificationMapper  extends BaseMapper<WillpowerClassification> {

    int insertBatchSomeColumn(Collection<WillpowerClassification> entityList);
}
