package com.pond.build.model.mimicry.response;

import lombok.Data;

import java.io.Serializable;

/**
 * 拟态图鉴(Mimicry)实体类
 *
 * @author makejava
 * @since 2024-11-14 14:32:18
 */
@Data
public class MimicryIdName implements Serializable {
    private static final long serialVersionUID = -96439024376235408L;
    /**
     * 拟态id
     */
    private Integer mimicryId;
    /**
     * 拟态名称
     */
    private String mimicryName;
}

