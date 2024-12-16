package com.pond.build.mapper.nuoCoins;


import com.pond.build.mapper.FishBaseMapper;
import com.pond.build.model.nuoCoins.NuoCoinsTaskWeekly;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NuoCoinsTaskWeeklyMapper extends FishBaseMapper<NuoCoinsTaskWeekly> {
}
