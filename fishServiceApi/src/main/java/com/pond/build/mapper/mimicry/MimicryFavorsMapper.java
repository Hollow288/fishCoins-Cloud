package com.pond.build.mapper.mimicry;

import com.pond.build.mapper.FishBaseMapper;
import com.pond.build.model.mimicry.MimicryFavors;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MimicryFavorsMapper  extends FishBaseMapper<MimicryFavors> {
}
