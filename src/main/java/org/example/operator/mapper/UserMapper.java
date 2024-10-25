package org.example.operator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.operator.pojo.entity.User;

@Mapper
public interface UserMapper {

    User getByUsername(String username);

    void register(User user);

    User login(User user);
}
