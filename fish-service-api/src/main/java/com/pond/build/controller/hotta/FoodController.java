package com.pond.build.controller.hotta;

import com.pond.build.aop.InjectUserDetails;
import com.pond.build.model.CommonResult;
import com.pond.build.model.food.Food;
import com.pond.build.model.TokenUser;
import com.pond.build.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService foodService;


    @PostMapping("/add-or-edit-food")
    @PreAuthorize("hasRole('ADMIN')")
    @InjectUserDetails
    public CommonResult<Food> addFood(@RequestBody Food food, TokenUser user){
        return foodService.addOrEditFood(food,user);
    }


    @GetMapping("/page-food")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Map<String,Object>> foodByPage(@RequestParam(value = "page") Integer page,
                                                       @RequestParam(value = "page_size") Integer pageSize,
                                                       @RequestParam(value = "search_name", defaultValue = "") String searchName){
        return foodService.foodByPage(page,pageSize,searchName);
    }

    @PutMapping("/delete-food")
    @PreAuthorize("hasRole('ADMIN')")
    @InjectUserDetails
    public CommonResult<Object> deleteFood(@RequestBody Map<String,Object> foodIds, TokenUser user){
        return foodService.deleteFood(foodIds,user);
    }

    @GetMapping("/id-food/{food_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Food> foodById(@PathVariable(value = "food_id") Integer foodId){
        return foodService.foodById(foodId);
    }


    @GetMapping("/food-basic")
    @PreAuthorize("hasRole('ADMIN')")
    public CommonResult<Map<String,Object>> foodBasic(){
        return foodService.foodBasic();
    }

}
