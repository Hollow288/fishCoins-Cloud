package com.pond.build.model.nuoCoins;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 每周诺元任务明细(NuoCoinsTaskWeeklyDetail)实体类
 *
 * @author makejava
 * @since 2024-12-12 17:30:48
 */
@Data
@TableName("nuo_coins_task_weekly_detail")
public class NuoCoinsTaskWeeklyDetail implements Serializable {
    private static final long serialVersionUID = -38888155482574877L;
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

