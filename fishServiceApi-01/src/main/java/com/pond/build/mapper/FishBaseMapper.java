package com.pond.build.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.toolkit.Db;

import java.util.Collection;

public interface FishBaseMapper<T> extends BaseMapper<T> {

    default boolean saveOrUpdate(T entity) {
        return Db.saveOrUpdate(entity);
    }

    default Boolean saveOrUpdateBatch(Collection<T> collection) {
        return Db.saveOrUpdateBatch(collection);
    }


}
