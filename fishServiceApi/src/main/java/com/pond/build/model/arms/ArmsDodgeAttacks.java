package com.pond.build.model.arms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 武器闪攻(ArmsDodgeAttacks)实体类
 *
 * @author makejava
 * @since 2024-11-06 09:54:24
 */
@Data
@TableName("arms_dodge_attacks")
public class ArmsDodgeAttacks implements Serializable {
    private static final long serialVersionUID = -95081041266803144L;
    /**
     * 闪攻id
     */
    @TableId(type = IdType.AUTO)
    private Integer dodgeAttacksId;
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

