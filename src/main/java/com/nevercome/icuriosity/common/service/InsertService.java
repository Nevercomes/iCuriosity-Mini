package com.nevercome.icuriosity.common.service;

/**
 * Created by sun on 2019/6/18
 */
public interface InsertService<E, PK> {

    /**
     * 插入一条记录
     * @param record 待插入的数据
     * @return 插入后返回数据的主键
     */
    PK insert(E record);

}
