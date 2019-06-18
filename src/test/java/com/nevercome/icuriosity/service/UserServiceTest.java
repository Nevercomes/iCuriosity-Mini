package com.nevercome.icuriosity.service;

import com.nevercome.icuriosity.BaseTest;
import com.nevercome.icuriosity.domain.User;
import com.nevercome.icuriosity.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sun on 2019/6/18
 */
public class UserServiceTest extends BaseTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void test() {
        List<User> userList = userService.selectAll();
        System.out.println(userList);
    }


}
