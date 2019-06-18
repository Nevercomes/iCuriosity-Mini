package com.nevercome.icuriosity.common.persistence;

/**
 * @author: sun
 * @date: 2019/6/18
 */
public interface CrudMapper<T> extends
        InsertMapper<T>,
        DeleteMapper<T>,
        UpdateMapper<T>,
        SelectMapper<T> {
}

