package com.pond.build.mapper.arms;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pond.build.model.arms.Arms;
import com.pond.build.model.arms.ArmsExclusives;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Mapper
@Repository
public interface ArmsMapper extends BaseMapper<Arms> {

    int insertBatchSomeColumn(Collection<Arms> entityList);

    List<Arms> getAllArmsInfoByPage(@Param("offset") int offset, @Param("limit") int limit, @Param("searchList") List<String> searchList);
}
