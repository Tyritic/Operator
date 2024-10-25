package org.example.operator.service;

import org.example.operator.pojo.dto.User.UserLoginDTO;
import org.example.operator.pojo.entity.User;

public interface UserLoginService {
    public User login(UserLoginDTO userLoginDTO);
}
