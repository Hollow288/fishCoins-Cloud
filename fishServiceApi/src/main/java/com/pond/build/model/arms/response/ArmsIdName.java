package com.pond.build.model.arms.response;

import lombok.Data;

import java.io.Serializable;
@Data
public class ArmsIdName implements Serializable {

    private static final long serialVersionUID = -17498112915612205L;
    /**
     * 武器ID
     */
    private Integer armsId;
    /**
     * 武器名称
     */
    private String armsName;
}
