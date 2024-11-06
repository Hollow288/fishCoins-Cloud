package com.pond.build.model.arms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 武器专属(ArmsExclusives)实体类
 *
 * @author makejava
 * @since 2024-11-06 09:54:30
 */
@Data
@TableName("arms_exclusives")
public class ArmsExclusives implements Serializable {
    private static final long serialVersionUID = 208555046045088999L;
    /**
     * 专属id
     */
    @TableId(type = IdType.AUTO)
    private Integer exclusivesId;
    /**
     * 武器id
     */
    private Integer armsId;
    /**
     * 词条名称
     */
    private String itemsName;
    /**
     * 词条描述
     */
    private String itemsDescribe;

}

