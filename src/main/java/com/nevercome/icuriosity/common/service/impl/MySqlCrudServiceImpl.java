package com.nevercome.icuriosity.common.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nevercome.icuriosity.common.persistence.po.PO;
import com.nevercome.icuriosity.common.persistence.page.PageQO;
import com.nevercome.icuriosity.common.persistence.page.PageVO;
import com.nevercome.icuriosity.common.service.CrudService;
import com.nevercome.icuriosity.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * Created by sun on 2019/6/18
 */
@Slf4j
public class MySqlCrudServiceImpl<E extends PO<PK>, PK> implements CrudService<E, PK> {

    @Autowired
    private Mapper<E> mapper;

    protected Class<E> poType;

    @SuppressWarnings("unchecked")
    public MySqlCrudServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        poType = (Class<E>) pt.getActualTypeArguments()[0];
    }


    @Override
    public int deleteByPk(PK pk) {
        Assert.notNull(pk, "mybatis delete forbidden: pk is null");
        return mapper.deleteByPrimaryKey(pk);
    }

    @Override
    public int deleteByPks(Iterable<PK> pks) {
        // TODO tk的配置
        Assert.notNull(pks, "mybatis batch delete forbidden: pks is null");
        String pksStr = this.IterableToSpitStr(pks, ",");
        if (StringUtils.isEmpty(pksStr)) {
            return 0;
        }
        return 0;
    }

    @Override
    public PK insert(E record) {
        Assert.notNull(record, "mybatis insert forbidden: record is null");

        if (record.getCreateTime() == null) {
            Date now = new Date();
            record.setCreateTime(now);
            record.setUpdateTime(now);
        }

        mapper.insert(record);
        return record.getId();

    }

    @Override
    public E selectByPk(PK pk) {
        Assert.notNull(pk, "mybatis select by pk forbidden: pk is null");
        return mapper.selectByPrimaryKey(pk);
    }

    @Override
    public List<E> selectByPks(Iterable<PK> pks) {
        // TODO tk的配置
        Assert.notNull(pks, "mybatis batch select by pks forbidden: pks is null");
        String pkStr = this.IterableToSpitStr(pks, ",");
        if (StringUtils.isEmpty(pkStr)) {
            Assert.notNull(pks, "mybatis batch select by pks forbidden: pks is null");
        }
        return null;
    }

    @Override
    public List<E> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public PageVO<E> selectPage(PageQO<?> pageQO) {
        Assert.notNull(pageQO, "pageQO is not null");

        Page<E> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize(), pageQO.getOrderBy());
        try {
            Object condition = pageQO.getCondition();
            if (condition == null) {
                mapper.selectAll();
            } else if (condition instanceof Condition) {
//                mapper.selectByCondition(condition);
            } else if (condition instanceof Example) {
                mapper.selectByExample(condition);
            } else if (poType.isInstance(condition)) {
                mapper.select((E) condition);
            } else {
                try {
                    E e = poType.newInstance();
                    BeanUtils.copyProperties(condition, e);
                    mapper.select(e);
                } catch (InstantiationException | IllegalAccessException e) {
                    log.error("selectPage occurs error, caused by: ", e);
                    throw new RuntimeException("poType.newInstance occurs InstantiationException or IllegalAccessException", e);
                }
            }
        } finally {
            page.close();
        }

        return PageVO.build(page);
    }

    @Override
    public int updateByPk(PK pk, E record) {
        Assert.notNull(pk, "mybatis update forbidden: pk is null");
        Assert.notNull(record, "mybatis update forbidden: record is null");

        record.setId(pk);
        if (record.getUpdateTime() == null || record.getUpdateTime().before(new Date())) {
            record.setUpdateTime(new Date());
        }
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPk(E record) {
        Assert.notNull(record, "mybatis update forbidden: record is null");
        Assert.notNull(record.getId(), "mybatis update forbidden: pk is null");

        if (record.getUpdateTime() == null || record.getUpdateTime().before(new Date())) {
            record.setUpdateTime(new Date());
        }
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPkSelective(PK pk, E record) {
        Assert.notNull(pk, "mybatis update forbidden: pk is null");
        Assert.notNull(record, "mybatis update forbidden: record is null");

        record.setId(pk);

        if (record.getUpdateTime() == null) {
            record.setUpdateTime(new Date());
        }
        return mapper.updateByPrimaryKeySelective(record);

    }

    @Override
    public int updateByPkSelective(E record) {
        Assert.notNull(record, "mybatis update forbidden: record is null");
        Assert.notNull(record.getId(), "mybatis update forbidden: pk is null");

        if (record.getUpdateTime() == null) {
            record.setUpdateTime(new Date());
        }
        return mapper.updateByPrimaryKeySelective(record);

    }

    @Override
    public PK saveOrUpdate(E record) {
        Assert.notNull(record, "mybatis save forbidden: record is null");

        if (record.getId() != null && selectByPk(record.getId()) != null) {
            updateByPk(record.getId(), record);
        } else {
            insert(record);
        }
        return record.getId();

    }

    private String IterableToSpitStr(Iterable<PK> pks, String separator) {
        StringBuilder s = new StringBuilder();
        pks.forEach(pk -> s.append(pk).append(separator));

        if (StringUtils.isEmpty(s.toString())) {
            return null;
        } else {
            s.deleteCharAt(s.length() - 1);
        }
        return s.toString();
    }

}
