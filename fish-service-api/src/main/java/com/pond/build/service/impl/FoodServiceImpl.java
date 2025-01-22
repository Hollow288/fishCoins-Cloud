package com.pond.build.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pond.build.enums.HttpStatusCode;
import com.pond.build.mapper.food.FoodFormulaMapper;
import com.pond.build.mapper.food.FoodMapper;
import com.pond.build.model.CommonResult;
import com.pond.build.model.food.Food;
import com.pond.build.model.TokenUser;
import com.pond.build.model.food.FoodFormula;
import com.pond.build.model.food.response.FoodIdName;
import com.pond.build.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class FoodServiceImpl implements FoodService {


    @Autowired
    private FoodMapper foodMapper;

    @Autowired
    private FoodFormulaMapper foodFormulaMapper;



    @Override
    public CommonResult<Food> addOrEditFood(Food food, TokenUser user) {
        foodMapper.saveOrUpdate(food);
        foodFormulaMapper.deleteDetailByFoodId(food.getFoodId());

        food.getFoodFormula().forEach(foodFormula -> foodFormula.setFoodId(food.getFoodId()));
        if (!CollectionUtils.isEmpty(food.getFoodFormula()))foodFormulaMapper.insertBatchSomeColumn(food.getFoodFormula());

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功",food);
    }


    @Override
    public CommonResult<Object> deleteFood(Map<String, Object> foodIds, TokenUser user) {
        List<String> foodList = (List<String>)foodIds.get("foodIds");
        LambdaUpdateWrapper<Food> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(Food::getFoodId, foodList)
                .set(Food::getDelFlag, "1");

        // 执行批量更新操作
        // 在 update(T entity, Wrapper<T> updateWrapper) 时，entity 不能为空，否则自动填充失效。
        boolean updateResult = foodMapper.update(null, updateWrapper) > 0;

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"操作成功");
    }

    @Override
    public CommonResult<Map<String,Object>> foodByPage(Integer page, Integer pageSize, String searchName) {

        Page<Food> pages = new Page<>(page, pageSize);
        LambdaQueryWrapper<Food> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Food::getDelFlag, "0");
        lambdaQueryWrapper.like(Food::getFoodName,searchName);
        lambdaQueryWrapper.orderByAsc(Food::getFoodId);
        Page<Food> foodPage = foodMapper.selectPage(pages, lambdaQueryWrapper);
        List<Food> records = foodPage.getRecords();
        long total = foodPage.getTotal();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("data", records);
        resultMap.put("page", page);
        resultMap.put("pageSize", pageSize);


        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",resultMap);
    }


    @Override
    public CommonResult<Food> foodById(Integer foodId) {
        LambdaQueryWrapper<Food> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Food::getFoodId,foodId);
        Food food = foodMapper.selectOne(lambdaQueryWrapper);

        LambdaQueryWrapper<FoodFormula> foodFormulaLambdaQueryWrapper = new LambdaQueryWrapper<>();
        foodFormulaLambdaQueryWrapper.eq(FoodFormula::getFoodId, foodId);
        List<FoodFormula> foodFormulas = foodFormulaMapper.selectList(foodFormulaLambdaQueryWrapper);
        food.setFoodFormula(foodFormulas);

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",food);
    }

    @Override
    public CommonResult<Map<String, Object>> foodBasic() {
        List<FoodIdName> foodIdNames = foodMapper.selectFoodIdName();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("foodIdNames",foodIdNames);

        return new CommonResult<>(HttpStatusCode.OK.getCode(),"查询成功",resultMap);
    }


}
