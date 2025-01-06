package com.pond.build.mapper.willpower;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.willpower.WillpowerSuit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface WillpowerSuitMapper  extends BaseMapper<WillpowerSuit> {

    int insertBatchSomeColumn(Collection<WillpowerSuit> entityList);
}
