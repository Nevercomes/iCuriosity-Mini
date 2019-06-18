package com.nevercome.icuriosity.persistence;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nevercome.icuriosity.BaseTest;
import com.nevercome.icuriosity.common.persistence.page.PageQO;
import com.nevercome.icuriosity.common.persistence.page.PageVO;
import com.nevercome.icuriosity.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: sun
 * @date: 2019/6/18
 */
public class PageTest  extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void pageTest() {
        PageQO pageQO = new PageQO(2, 2);
        Page<User> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize(), pageQO.getOrderBy());
        userMapper.selectAll();
        PageVO<User> pageVO = PageVO.build(page);

        System.out.println(pageVO.getList().get(0).getId());
    }

}
