package com.nevercome.icuriosity.common.service;

/**
 * Created by sun on 2019/6/18
 */
public interface DeleteService<PK> {

    /**
     * 根据主键删除记录
     *
     * @param pk 主键
     * @return 影响记录数
     */
    int deleteByPk(PK pk);

    /**
     * 根据主键删除记录
     *
     * @param pks 主键集合
     * @return 影响记录数
     */
    int deleteByPks(Iterable<PK> pks);

}
