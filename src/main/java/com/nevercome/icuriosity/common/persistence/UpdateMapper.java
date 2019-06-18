package com.nevercome.icuriosity.common.persistence;

import org.apache.logging.log4j.Marker;
import tk.mybatis.mapper.common.base.update.UpdateByPrimaryKeySelectiveMapper;
import tk.mybatis.mapper.common.condition.UpdateByConditionMapper;
import tk.mybatis.mapper.common.condition.UpdateByConditionSelectiveMapper;
import tk.mybatis.mapper.common.example.UpdateByExampleSelectiveMapper;

/**
 * @author: sun
 * @date: 2019/6/18
 */
public interface UpdateMapper<T> extends Marker,
        UpdateByPrimaryKeySelectiveMapper<T>,
        UpdateByConditionMapper<T>,
        UpdateByConditionSelectiveMapper<T>,
        UpdateByExampleSelectiveMapper<T> {
}
