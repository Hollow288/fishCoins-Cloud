package com.pond.build.model.mimicry;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 拟态好感度(MimicryFavors)实体类
 *
 * @author makejava
 * @since 2024-11-14 14:34:57
 */
@Data
@TableName("mimicry_favors")
public class MimicryFavors implements Serializable {
    private static final long serialVersionUID = 184984915448171306L;
    /**
     * 好感度id
     */
    @TableId(type = IdType.AUTO)
    private Integer favorsId;
    /**
     * 好感等级
     */
    private String favorsLevel;
    /**
     * 好感奖励
     */
    private String favorsReward;
    /**
     * 拟态id
     */
    private Integer mimicryId;

}

