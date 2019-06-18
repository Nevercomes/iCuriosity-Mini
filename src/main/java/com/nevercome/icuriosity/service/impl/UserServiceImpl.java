package com.nevercome.icuriosity.service.impl;

import com.nevercome.icuriosity.common.service.impl.MySqlCrudServiceImpl;
import com.nevercome.icuriosity.domain.User;
import com.nevercome.icuriosity.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by sun on 2019/6/18
 */
@Service
public class UserServiceImpl extends MySqlCrudServiceImpl<User, String> implements UserService {
}
