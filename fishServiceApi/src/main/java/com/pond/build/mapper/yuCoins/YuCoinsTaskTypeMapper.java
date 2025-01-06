package com.pond.build.mapper.yuCoins;

import com.pond.build.mapper.FishBaseMapper;
import com.pond.build.model.yuCoins.YuCoinsTaskType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Mapper
@Repository
public interface YuCoinsTaskTypeMapper extends FishBaseMapper<YuCoinsTaskType> {
}
