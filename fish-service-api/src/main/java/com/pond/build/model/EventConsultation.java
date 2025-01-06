package com.pond.build.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (EventConsultation)实体类
 *
 * @author makejava
 * @since 2024-12-30 16:31:50
 */
@Data
@TableName("event_consultation")
public class EventConsultation implements Serializable {
    private static final long serialVersionUID = -55257374761359687L;
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer consultationId;
    /**
     * 活动标题
     */
    private String consultationTitle;
    /**
     * 活动描述
     */
    private String consultationDescribe;
    /**
     * 活动缩略图地址
     */
    private String consultationThumbnailUrl;
    /**
     * 活动开始时间
     */
    private Date consultationStart;
    /**
     * 活动结束时间
     */
    private Date consultationEnd;
    /**
     * 是否删除（0未删除 1已删除）
     */
    private String delFlag;

}

