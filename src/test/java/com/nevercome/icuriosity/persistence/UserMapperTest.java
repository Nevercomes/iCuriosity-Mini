package com.nevercome.icuriosity.persistence;

import com.nevercome.icuriosity.BaseTest;
import com.nevercome.icuriosity.IcuriosityApplicationTests;
import com.nevercome.icuriosity.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: sun
 * @date: 2019/6/18
 */

public class UserMapperTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        userMapper.insert(new User("123", "test"));
    }

}
