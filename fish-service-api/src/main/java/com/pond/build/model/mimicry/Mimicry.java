package com.pond.build.model.mimicry;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * 拟态图鉴(Mimicry)实体类
 *
 * @author makejava
 * @since 2024-11-14 14:32:18
 */
@Data
@TableName("mimicry")
public class Mimicry implements Serializable {
    private static final long serialVersionUID = -66439024376235408L;
    /**
     * 拟态id
     */
    @TableId(type = IdType.AUTO)
    private Integer mimicryId;
    /**
     * 拟态名称
     */
    private String mimicryName;
    /**
     * 拟态配音
     */
    private String mimicryCv;
    /**
     * 拟态性别
     */
    private String mimicrySex;
    /**
     * 拟态身高(cm)
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private Integer mimicryHeight;
    /**
     * 拟态所属
     */
    private String mimicryFactions;
    /**
     * 拟态生日
     */
    private String mimicryBirthday;
    /**
     * 拟态喜好
     */
    private String mimicryLike;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新人
     */
    private Long updateBy;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    /**
     * 是否删除（0未删除 1已删除）
     */
    private String delFlag;
    /**
     * 拟态好感度
     */
    @TableField(exist = false)
    private List<MimicryFavors> mimicryFavors;


}

