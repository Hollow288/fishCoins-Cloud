package com.pond.build.model.yuCoins;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 每周域币任务种类(YuCoinsTaskType)实体类
 *
 * @author makejava
 * @since 2024-12-05 15:32:51
 */
@Data
@TableName("yu_coins_task_type")
public class YuCoinsTaskType implements Serializable {
    private static final long serialVersionUID = 277111017139806844L;
    /**
     * 种类ID
     */
    @TableId
    private Integer taskTypeId;
    /**
     * 地区
     */
    private String taskTypeRegion;
    /**
     * NPC
     */
    private String taskTypeNpc;
    /**
     * 位置
     */
    private String taskTypePosition;
    /**
     * 任务详情
     */
    private String taskTypeDetails;
    /**
     * 任务奖励
     */
    private String taskTypeReward;
    /**
     * 是否删除（0未删除 1已删除）
     */
    private String delFlag;

}

