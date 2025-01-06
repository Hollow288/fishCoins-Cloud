package com.pond.build.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        // 插入时填充字段
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());  // 创建时间填充当前时间
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());  // 更新时间填充当前时间
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时填充字段
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());  // 更新时间填充当前时间
    }
}
