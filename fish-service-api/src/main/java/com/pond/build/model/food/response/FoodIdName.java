package com.pond.build.model.food.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class FoodIdName implements Serializable {

    private static final long serialVersionUID = 712860695145261941L;
    /**
     * 食物ID
     */
    @TableId(type = IdType.AUTO)
    private Integer foodId;
    /**
     * 食物名称
     */
    private String foodName;
}
