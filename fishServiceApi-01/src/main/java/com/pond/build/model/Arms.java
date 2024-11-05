package com.pond.build.model;

import com.pond.build.model.basic.ItemsBasic;

import java.math.BigDecimal;
import java.util.List;

public class Arms {

    /**
     * 武器ID
     */
    private String armsId;
    /**
     * 武器名称
     */
    private String armsName;
    /**
     * 武器稀有度
     */
    private String armsRarity;
    /**
     * 武器轮次
     */
    private String armsDate;
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
     * 武器特质
     */
    private List<String> armsCharacteristics;
    /**
     * 武器特质
     */
    private List<ItemsBasic> armsTraits;
    /**
     * 武器专属
     */
    private List<ItemsBasic> armsExclusives;
    /**
     * 武器星级
     */
    private List<ItemsBasic> armsStarRatings;
    /**
     * 武器普攻
     */
    private List<ItemsBasic> armsPrimaryAttacks;
    /**
     * 武器闪避攻击
     */
    private List<ItemsBasic> armsDodgeAttacks;
    /**
     * 武器技能攻击
     */
    private List<ItemsBasic> armsSkillAttacks;
    /**
     * 武器联携攻击
     */
    private List<ItemsBasic> armsCooperationAttacks;




}
