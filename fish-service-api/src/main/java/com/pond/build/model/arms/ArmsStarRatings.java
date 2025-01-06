package com.pond.build.model.arms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 武器星级(ArmsStarRatings)实体类
 *
 * @author makejava
 * @since 2024-11-06 09:54:46
 */
@Data
@TableName("arms_star_ratings")
public class ArmsStarRatings implements Serializable {
    private static final long serialVersionUID = 666753422812378875L;
    /**
     * 星级id
     */
    @TableId(type = IdType.AUTO)
    private Integer starRatingsId;
    /**
     * 武器id
     */
    private Integer armsId;
    /**
     * 词条名称
     */
    private String itemsName;
    /**
     * 词条描述
     */
    private String itemsDescribe;

}

