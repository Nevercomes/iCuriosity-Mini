package com.nevercome.icuriosity.common.service;

/**
 * Created by sun on 2019/6/18
 */
public interface CrudService<E, PK> extends
        InsertService<E, PK>,
        UpdateService<E, PK>,
        DeleteService<PK>,
        SelectService<E, PK> {
}

