package com.pond.build.mapper.food;

import com.pond.build.mapper.FishBaseMapper;
import com.pond.build.model.food.FoodFormula;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Mapper
@Repository
public interface FoodFormulaMapper extends FishBaseMapper<FoodFormula> {

    int insertBatchSomeColumn(Collection<FoodFormula> entityList);

    void deleteDetailByFoodId(@Param("foodId") Integer foodId);

}
