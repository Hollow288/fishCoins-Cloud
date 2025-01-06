package com.pond.build.mapper.arms;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.arms.ArmsSkillAttacks;
import com.pond.build.model.arms.ArmsStarRatings;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface ArmsStarRatingsMapper  extends BaseMapper<ArmsStarRatings> {

    int insertBatchSomeColumn(Collection<ArmsStarRatings> entityList);
}
