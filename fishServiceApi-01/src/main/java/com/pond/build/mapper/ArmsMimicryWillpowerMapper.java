package com.pond.build.mapper;


import com.pond.build.model.ArmsMimicryWillpower;
import com.pond.build.model.arms.response.ArmsIdName;
import com.pond.build.model.mimicry.response.MimicryIdName;
import com.pond.build.model.willpower.response.WillpowerIdName;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ArmsMimicryWillpowerMapper extends FishBaseMapper<ArmsMimicryWillpower> {

    List<ArmsIdName> selectArmsIdName();

    List<MimicryIdName> selectMimicryIdName();

    List<WillpowerIdName> selectWillpowerIdName();



}
