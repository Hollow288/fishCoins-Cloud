package com.pond.build.model.food;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 食物配方(FoodFormula)实体类
 *
 * @author makejava
 * @since 2025-01-22 10:13:41
 */
@Data
@TableName("food_formula")
public class FoodFormula implements Serializable {
    private static final long serialVersionUID = -80066021573251764L;
    /**
     * 配方ID
     */
    @TableId(type = IdType.AUTO)
    private Integer formulaId;
    /**
     * 食物ID
     */
    private Integer foodId;
    /**
     * 食材ID
     */
    private Integer ingredientsId;
    /**
     * 食材数量
     */
    private Integer ingredientsNum;
}

