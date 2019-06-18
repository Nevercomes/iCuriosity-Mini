package com.nevercome.icuriosity.common.persistence;

import tk.mybatis.mapper.common.Marker;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.base.insert.InsertSelectiveMapper;

/**
 * @author: sun
 * @date: 2019/6/18
 */
public interface InsertMapper<T> extends Marker,
        tk.mybatis.mapper.common.base.insert.InsertMapper<T>,
        InsertSelectiveMapper<T>,
        MySqlMapper<T> {
}
