package com.pond.build.model.willpower;

import java.util.List;

public class Willpower {

    /**
     * 意志ID
     */
    private Integer willpowerId;

    /**
     * 意志名称
     */
    private String willpowerName;

    /**
     * 意志星级
     */
    private String willpowerRarity;

    /**
     * 意志描述
     */
    private String willpowerDescription;

    /**
     * 关联武器ID
     */
    private Integer armsId;

    /**
     * 关联武器名称
     */
    private Integer armsName;

    /**
     * 意志分类介绍
     */
    private List<WillpowerClassification> willpowerClassification;


}
