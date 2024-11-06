package com.pond.build.model.arms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 武器特质(ArmsCharacteristics)实体类
 *
 * @author makejava
 * @since 2024-11-06 09:50:25
 */
@Data
@TableName("arms_characteristics")
public class ArmsCharacteristics implements Serializable {
    private static final long serialVersionUID = -17497182916112205L;
    /**
     * 特质id
     */
    @TableId(type = IdType.AUTO)
    private Integer characteristicsId;
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

