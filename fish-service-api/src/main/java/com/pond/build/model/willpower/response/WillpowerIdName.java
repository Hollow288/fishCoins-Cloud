package com.pond.build.model.willpower.response;


import lombok.Data;

import java.io.Serializable;

@Data
public class WillpowerIdName implements Serializable {

    private static final long serialVersionUID = -97497182915612105L;

    /**
     * 意志ID
     */
    private Integer willpowerId;

    /**
     * 意志名称
     */
    private String willpowerName;
}
