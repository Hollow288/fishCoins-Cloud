package com.pond.build.model.food;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 食物图鉴(Food)实体类
 *
 * @author makejava
 * @since 2025-01-22 10:13:33
 */
@Data
@TableName("food")
public class Food implements Serializable {
    private static final long serialVersionUID = 712860695145261946L;
    /**
     * 食物ID
     */
    @TableId(type = IdType.AUTO)
    private Integer foodId;
    /**
     * 食物名称
     */
    private String foodName;
    /**
     * 食物稀有度
     */
    private String foodRarity;
    /**
     * 食物种类
     */
    private String foodType;
    /**
     * 食物效果
     */
    private String foodEffect;
    /**
     * 食物来源
     */
    private String foodSource;
    /**
     * 食物描述
     */
    private String foodDescribe;
    /**
     * 食物缩略图地址
     */
    private String foodThumbnailUrl;
    /**
     * 是否删除（0未删除 1已删除）
     */
    private String delFlag;

    /**
     * 食物配方
     */
    @TableField(exist = false)
    private List<FoodFormula> foodFormula;

}

