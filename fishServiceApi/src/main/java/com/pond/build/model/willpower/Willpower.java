package com.pond.build.model.willpower;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@TableName("willpower")
public class Willpower implements Serializable {

    private static final long serialVersionUID = -17497182915612105L;

    /**
     * 意志ID
     */
    @TableId(type = IdType.AUTO)
    private Integer willpowerId;

    /**
     * 意志名称
     */
    private String willpowerName;

    /**
     * 意志稀有度
     */
    private String willpowerRarity;

    /**
     * 意志描述
     */
    private String willpowerDescription;

    /**
     *  意志缩略图
     */
    private String willpowerThumbnailUrl;


    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String delFlag;

    /**
     * 意志分类介绍
     */
    @TableField(exist = false)
    private List<WillpowerClassification> willpowerClassification;

    /**
     * 意志套装
     */
    @TableField(exist = false)
    private List<WillpowerSuit> willpowerSuit;


}
