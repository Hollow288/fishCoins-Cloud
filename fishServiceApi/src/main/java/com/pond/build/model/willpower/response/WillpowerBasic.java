package com.pond.build.model.willpower.response;

import com.baomidou.mybatisplus.annotation.*;
import com.pond.build.model.willpower.WillpowerClassification;
import com.pond.build.model.willpower.WillpowerSuit;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class WillpowerBasic implements Serializable {

    private static final long serialVersionUID = -17497182915612209L;

    /**
     * 意志ID
     */
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
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private String delFlag;


}
