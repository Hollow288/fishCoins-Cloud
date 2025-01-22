package com.pond.build.service;

import com.pond.build.model.CommonResult;
import com.pond.build.model.food.Food;
import com.pond.build.model.TokenUser;

import java.util.Map;

public interface FoodService {
    CommonResult<Food> addOrEditFood(Food food, TokenUser user);

    CommonResult<Object> deleteFood(Map<String,Object> foodIds, TokenUser user);

    CommonResult<Map<String,Object>> foodByPage(Integer page, Integer pageSize, String searchName);

    CommonResult<Food> foodById(Integer foodId);

    CommonResult<Map<String, Object>> foodBasic();
}
