package com.pond.build.mapper.mimicry;

import com.pond.build.mapper.FishBaseMapper;
import com.pond.build.model.mimicry.Mimicry;
import com.pond.build.model.willpower.Willpower;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MimicryMapper extends FishBaseMapper<Mimicry> {

    Mimicry getMimicryInfoById(@Param("mimicryId") Integer mimicryId);

    void deleteDetailByMimicryId(@Param("mimicryId") Integer mimicryId);
}
