package com.pond.build.controller.hotta;


import com.pond.build.remote.hotta.food.FoodRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodRemote foodRemote;

    @PostMapping("/add-or-edit-food")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> addOrEditFood(@RequestBody Object food){
        return foodRemote.addOrEditFood(food);
    }


    @PutMapping("/delete-food")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> deleteFood(@RequestBody Object foodIds){
        return foodRemote.deleteFood(foodIds);
    }


    @GetMapping("/page-food")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> foodByPage(@RequestParam(value = "page") Integer page,
                                                       @RequestParam(value = "page_size") Integer pageSize,
                                                       @RequestParam(value = "search_name", defaultValue = "") String searchName){
        return foodRemote.foodByPage(page,pageSize,searchName);
    }


    @GetMapping("/id-food/{food_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> foodById(@PathVariable(value = "food_id") Integer foodId){
        return foodRemote.foodById(foodId);
    }

    @GetMapping("/food-basic")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String,Object> foodBasic(){
        return foodRemote.foodBasic();
    }


}
