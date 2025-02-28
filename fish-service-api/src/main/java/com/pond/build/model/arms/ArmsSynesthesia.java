package com.pond.build.model.arms;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 武器通感(ArmsSynesthesia)实体类
 *
 * @author makejava
 * @since 2024-11-06 09:50:25
 */
@Data
@TableName("arms_synesthesia")
public class ArmsSynesthesia implements Serializable {
    private static final long serialVersionUID = -17497182916112206L;
    /**
     * 通感id
     */
    @TableId(type = IdType.AUTO)
    private Integer synesthesiaId;
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

