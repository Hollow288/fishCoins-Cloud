package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 武器拟态意志关联(ArmsMimicryWillpower)实体类
 *
 * @author makejava
 * @since 2024-11-15 09:55:16
 */
@Data
@TableName("arms_mimicry_willpower")
public class ArmsMimicryWillpower implements Serializable {

    private static final long serialVersionUID = -83674111868094086L;
    /**
     * 武器ID
     */
    @TableId
    private Integer armsId;
    /**
     * 拟态ID
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private Integer mimicryId;
    /**
     * 意志ID
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private Integer willpowerId;


}

