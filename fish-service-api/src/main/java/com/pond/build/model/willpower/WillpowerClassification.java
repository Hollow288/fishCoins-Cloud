package com.pond.build.model.willpower;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("willpower_classification")
public class WillpowerClassification implements Serializable {

    private static final long serialVersionUID = -75081041266803144L;

    /**
     * 分类介绍ID
     */
    @TableId(type = IdType.AUTO)
    private Integer classificationId;

    /**
     * 意志种类
     */
    private String classificationType;

    /**
     * 意志名称
     */
    private String itemsName;

    /**
     * 意志描述
     */
    private String itemsDescribe;


    /**
     * 意志ID
     */
    private Integer willpowerId;


}
