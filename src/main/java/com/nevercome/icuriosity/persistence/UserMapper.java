package com.nevercome.icuriosity.persistence;

import com.nevercome.icuriosity.domain.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author: sun
 * @date: 2019/6/18
 */
@Repository
public interface UserMapper extends Mapper<User> {

    User customSelect(User user);

}
