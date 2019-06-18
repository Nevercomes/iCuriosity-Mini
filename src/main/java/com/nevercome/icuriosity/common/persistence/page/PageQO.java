package com.nevercome.icuriosity.common.persistence.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页查询对象
 *
 * @author: sun
 * @date: 2019/6/18
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageQO<T> {

    /**
     * 默认按创建时间降序排列
     */
    public static final String ORDER_BY_CREATE_DESC = "create_time desc";


    private int pageNum = 1;

    private int pageSize = 10;

    /**
     * 如果设置了orderBy的话 会覆盖掉原有查询中的orderBy
     */
    private String orderBy;

    private T condition;

    public PageQO(int pageNum, int pageSize) {
        super();
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getOffset() {
        return (pageNum - 1) * pageSize;
    }

}
