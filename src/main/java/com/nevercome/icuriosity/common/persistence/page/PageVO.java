package com.nevercome.icuriosity.common.persistence.page;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询响应对象
 *
 * @author: sun
 * @date: 2019/6/18
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageVO<T> implements Serializable {

    private static final long serialVersionUID = -4426958360243585882L;

    private int pageNum;

    private int pageSize;

    private long total;

    private int pages;

    private List<T> list;

    public PageVO(PageQO pageQO) {
        this.pageNum = pageQO.getPageNum();
        this.pageSize = pageQO.getPageSize();
    }

    public PageVO(List<T> poList) {
        BeanUtils.copyProperties(new PageInfo<>(poList), this);
    }

    /**
     * 根据结果集 构建一个PageVO
     */
    public static <T> PageVO<T> build(List<T> poList) {
        return new PageVO<>(poList);
    }

    /**
     * 构建一个PageVO
     *
     * @param page 数据库查询出来的分页数据
     */
    public static <T> PageVO<T> build(Page<T> page) {
        PageVO<T> pageVO = new PageVO<>();
        BeanUtils.copyProperties(page.toPageInfo(), pageVO);
        return pageVO;
    }

    /**
     * 适用场景为：从数据库取出的PO列表不做任何处理，转化为VO列表返回
     *
     * @param page    数据库查出来的分页数据列表
     * @param voClazz 要转化的VO类
     */
    public static <T, E> PageVO<T> build(Page<E> page, Class<T> voClazz) {
        PageVO<T> pageVO = new PageVO<>();
        BeanUtils.copyProperties(page, pageVO, "list");
        try {
            List<T> VOs = Lists.newArrayList();
            List<E> POs = page.getResult();
            if (!CollectionUtils.isEmpty(POs)) {
                for (E e : POs) {
                    T t = voClazz.newInstance();
                    BeanUtils.copyProperties(e, t);
                    VOs.add(t);
                }
            }
            pageVO.setList(VOs);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
        return pageVO;
    }

    /**
     * 适用场景为：将处理好的VO列表封装返回
     *
     * @param poPage
     * @param voList
     */
    public static <T, E> PageVO<T> build(Page<E> poPage, List<T> voList) {
        PageVO<T> pageVO = new PageVO<>();
        BeanUtils.copyProperties(poPage, pageVO, "list");
        pageVO.setList(voList == null ? Lists.newArrayList() : voList);
        return pageVO;
    }

    public static int getPages(long total, int pageSize) {
        if (total == 0 && pageSize == 0) {
            return 0;
        }
        return (int) (total % pageSize == 0 ? (total / pageSize) : (total / pageSize + 1));
    }

    public int getPages() {
        return getPages(this.total, this.pageSize);
    }

}
