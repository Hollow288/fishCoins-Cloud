package com.pond.build.model.arms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 武器普攻(ArmsPrimaryAttacks)实体类
 *
 * @author makejava
 * @since 2024-11-06 09:54:36
 */
@Data
@TableName("arms_primary_attacks")
public class ArmsPrimaryAttacks implements Serializable {
    private static final long serialVersionUID = -58961034007115621L;
    /**
     * 普攻id
     */
    @TableId(type = IdType.AUTO)
    private Integer primaryAttacksId;
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

