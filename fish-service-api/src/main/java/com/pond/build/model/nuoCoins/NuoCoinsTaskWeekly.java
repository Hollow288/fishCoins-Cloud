package com.pond.build.model.nuoCoins;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 每周诺元任务(NuoCoinsTaskWeekly)实体类
 *
 * @author makejava
 * @since 2024-12-05 15:46:48
 */
@Data
@TableName("nuo_coins_task_weekly")
public class NuoCoinsTaskWeekly implements Serializable {
    private static final long serialVersionUID = -82237710900743905L;
    /**
     * 任务ID
     */
    @TableId(type = IdType.AUTO)
    private Integer taskWeeklyId;
    /**
     * 发布时间
     */
    private Date taskWeeklyDate;
    /**
     * 是否删除（0未删除 1已删除）
     */
    private String delFlag;
    /**
     * 任务详情
     */
    @TableField(exist = false)
    private List<Integer> weeklyDetailIds;

}

