package com.pond.build.model.arms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 武器联携(ArmsCooperationAttacks)实体类
 *
 * @author makejava
 * @since 2024-11-06 09:54:17
 */
@Data
@TableName("arms_cooperation_attacks")
public class ArmsCooperationAttacks implements Serializable {
    private static final long serialVersionUID = 407963723028424111L;
    /**
     * 联携id
     */
    @TableId(type = IdType.AUTO)
    private Integer cooperationAttacksId;
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

