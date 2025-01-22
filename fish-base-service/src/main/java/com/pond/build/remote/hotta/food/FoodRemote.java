package com.pond.build.remote.hotta.food;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name= "fishServiceApi", contextId = "foodServiceClient")
public interface FoodRemote {

    String PREFIX = "/food";

    @PostMapping(PREFIX + "/add-or-edit-food")
    Map<String,Object> addOrEditFood(@RequestBody Object food);


    @PutMapping (value = PREFIX + "/delete-food")
    Map<String,Object> deleteFood(@RequestBody Object foodIds);

    @GetMapping(PREFIX + "/page-food")
    Map<String,Object> foodByPage(@RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "page_size") Integer pageSize,
                                       @RequestParam(value = "search_name", defaultValue = "") String searchName);

    @GetMapping(PREFIX + "/id-food/{food_id}")
    Map<String, Object> foodById(@PathVariable(value = "food_id") Integer foodId);

    @GetMapping(PREFIX + "/food-basic")
    Map<String, Object> foodBasic();
}
