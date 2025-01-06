package com.pond.build.model.yuCoins;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 每周域币任务明细(YuCoinsTaskWeeklyDetail)实体类
 *
 * @author makejava
 * @since 2024-12-12 17:30:48
 */
@Data
@TableName("yu_coins_task_weekly_detail")
public class YuCoinsTaskWeeklyDetail implements Serializable {
    private static final long serialVersionUID = -38788155482574877L;
/**
     * 明细ID
     */
    @TableId(type = IdType.AUTO)
    private Integer weeklyDetailId;
/**
     * 每周任务ID
     */
    private Integer taskWeeklyId;
/**
     * 任务种类ID
     */
    private Integer taskTypeId;
/**
     * 贡献者
     */
    private String taskWeeklyContributors;
/**
     * 是否删除（0未删除 1已删除）
     */
    private String delFlag;
}

