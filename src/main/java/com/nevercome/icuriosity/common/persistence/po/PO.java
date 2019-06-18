package com.nevercome.icuriosity.common.persistence.po;

import java.util.Date;

/**
 * Created by sun on 2019/6/18
 */
public interface PO<PK>  {

    PK getId();

    void setId(PK id);

    Date getCreateTime();

    void setCreateTime(Date createTime);

    Date getUpdateTime();

    void setUpdateTime(Date updateTime);

}
