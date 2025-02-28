package com.pond.build.model.arms;

import com.baomidou.mybatisplus.annotation.*;
import com.pond.build.utils.CommonUtil;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@TableName("arms")
public class Arms  implements Serializable {

    private static final long serialVersionUID = -17497182915612205L;
    /**
     * 武器ID
     */
    @TableId(type = IdType.AUTO)
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
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private BigDecimal armsOverwhelmed;
    /**
     * 武器充能
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private BigDecimal armsChargingEnergy;
    /**
     * 武器攻击力-初始
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private BigDecimal armsAggressivityStart;
    /**
     * 武器血量-初始
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private BigDecimal armsBloodVolumeStart;
    /**
     * 武器全抗-初始
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private BigDecimal armsDefenseCapabilityStart;
    /**
     * 武器暴击-初始
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private BigDecimal armsCriticalStrikeStart;
    /**
     * 武器攻击-满级
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private BigDecimal armsAggressivityEnd;
    /**
     * 武器血量-满级
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private BigDecimal armsBloodVolumeEnd;
    /**
     * 武器全抗-满级
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
    private BigDecimal armsDefenseCapabilityEnd;
    /**
     * 武器暴击-满级
     */
    @TableField(updateStrategy = FieldStrategy.ALWAYS)
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
     * 武器特质
     */
    @TableField(exist = false)
    private List<ArmsCharacteristics> armsCharacteristics;
    /**
     * 武器专属
     */
    @TableField(exist = false)
    private List<ArmsExclusives> armsExclusives;
    /**
     * 武器星级
     */
    @TableField(exist = false)
    private List<ArmsStarRatings> armsStarRatings;
    /**
     * 武器普攻
     */
    @TableField(exist = false)
    private List<ArmsPrimaryAttacks> armsPrimaryAttacks;
    /**
     * 武器闪避攻击
     */
    @TableField(exist = false)
    private List<ArmsDodgeAttacks> armsDodgeAttacks;
    /**
     * 武器技能攻击
     */
    @TableField(exist = false)
    private List<ArmsSkillAttacks> armsSkillAttacks;
    /**
     * 武器联携攻击
     */
    @TableField(exist = false)
    private List<ArmsCooperationAttacks> armsCooperationAttacks;
    /**
     * 武器通感
     */
    @TableField(exist = false)
    private List<ArmsSynesthesia> armsSynesthesia;

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

}
