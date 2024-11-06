package com.pond.build.model.arms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 武器技能(ArmsSkillAttacks)实体类
 *
 * @author makejava
 * @since 2024-11-06 09:54:41
 */
@Data
@TableName("arms_skill_attacks")
public class ArmsSkillAttacks implements Serializable {
    private static final long serialVersionUID = -50861909881126860L;
    /**
     * 技能id
     */
    @TableId(type = IdType.AUTO)
    private Integer skillAttacksId;
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

