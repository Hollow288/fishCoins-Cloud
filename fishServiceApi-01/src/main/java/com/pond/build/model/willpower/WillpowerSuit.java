package com.pond.build.model.willpower;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("willpower_suit")
public class WillpowerSuit {

    private static final long serialVersionUID = -17497172915612105L;

    /**
     * 意志套装ID
     */
    @TableId(type = IdType.AUTO)
    private Integer suitId;

    /**
     * 武器id
     */
    private Integer willpowerId;
    /**
     * 词条名称
     */
    private String itemsName;
    /**
     * 词条描述
     */
    private String itemsDescribe;

}
