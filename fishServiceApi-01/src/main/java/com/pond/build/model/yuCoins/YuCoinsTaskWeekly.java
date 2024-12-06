package com.pond.build.model.yuCoins;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 每周域币任务(YuCoinsTaskWeekly)实体类
 *
 * @author makejava
 * @since 2024-12-05 15:46:48
 */
@Data
@TableName("yu_coins_task_weekly")
public class YuCoinsTaskWeekly implements Serializable {
    private static final long serialVersionUID = -81237710900743905L;
    /**
     * 任务ID
     */
    @TableId(type = IdType.AUTO)
    private Integer taskWeeklyId;
    /**
     * 每周任务IDS
     */
    private String taskTypeIds;
    /**
     * 发布时间
     */
    private Date taskWeeklyDate;
    /**
     * 是否删除（0未删除 1已删除）
     */
    private String delFlag;

}

