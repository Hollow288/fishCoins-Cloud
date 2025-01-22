package com.pond.build.mapper.food;

import com.pond.build.mapper.FishBaseMapper;
import com.pond.build.model.food.Food;
import com.pond.build.model.food.response.FoodIdName;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FoodMapper extends FishBaseMapper<Food> {

    List<FoodIdName> selectFoodIdName();


}
