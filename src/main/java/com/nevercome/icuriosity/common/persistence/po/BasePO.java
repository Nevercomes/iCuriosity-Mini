package com.nevercome.icuriosity.common.persistence.po;

import lombok.Data;

import java.util.Date;

/**
 * Created by sun on 2019/6/18
 */
@Data
public abstract class BasePO<PK> implements PO<PK>{

    private Date createTime;

    private Date updateTime;

}
