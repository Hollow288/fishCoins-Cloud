package com.pond.build.model.arms.response;

import com.baomidou.mybatisplus.annotation.*;
import com.pond.build.model.arms.*;
import com.pond.build.utils.CommonUtil;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ArmsBasic implements Serializable {

    private static final long serialVersionUID = -17497112915612205L;
    /**
     * 武器ID
     */
    private Integer armsId;
    /**
     * 武器名称
     */
    private String armsName;
    /**
     * 武器稀有度
     */
    private String armsRarity;
    /**
     * 武器定位
     */
    private String armsType;
    /**
     * 武器属性
     */
    private String armsAttribute;
    /**
     * 武器破防
     */
    private BigDecimal armsOverwhelmed;
    /**
     * 武器充能
     */
    private BigDecimal armsChargingEnergy;
    /**
     * 武器攻击力-初始
     */
    private BigDecimal armsAggressivityStart;
    /**
     * 武器血量-初始
     */
    private BigDecimal armsBloodVolumeStart;
    /**
     * 武器全抗-初始
     */
    private BigDecimal armsDefenseCapabilityStart;
    /**
     * 武器暴击-初始
     */
    private BigDecimal armsCriticalStrikeStart;
    /**
     * 武器攻击-满级
     */
    private BigDecimal armsAggressivityEnd;
    /**
     * 武器血量-满级
     */
    private BigDecimal armsBloodVolumeEnd;
    /**
     * 武器全抗-满级
     */
    private BigDecimal armsDefenseCapabilityEnd;
    /**
     * 武器暴击-满级
     */
    private BigDecimal armsCriticalStrikeEnd;
    /**
     * 武器缩略图地址
     */
    private String armsThumbnailUrl;
    /**
     * 武器描述
     */
    private String armsDescription;
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

    public String getArmsThumbnailUrl() {
        return CommonUtil.fileUrlEncoderChance(armsThumbnailUrl);
    }
}
