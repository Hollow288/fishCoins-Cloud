package com.pond.build.mapper.willpower;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.arms.Arms;
import com.pond.build.model.willpower.Willpower;
import com.pond.build.model.willpower.response.WillpowerBasic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WillpowerMapper extends BaseMapper<Willpower> {


    Integer getCountWillpower(@Param("searchName") String searchName);

    List<WillpowerBasic> getWillpowerBasicInfoByPage(@Param("offset") int offset, @Param("limit") int limit, @Param("searchName") String searchName);

    List<Willpower> getWillpowerInfoByPage(@Param("offset") int offset, @Param("limit") int limit, @Param("searchName") String searchName);

}
