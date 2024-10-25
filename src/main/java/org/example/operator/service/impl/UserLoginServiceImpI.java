package org.example.operator.service.impl;

import org.example.operator.common.constant.LoginConstant;
import org.example.operator.common.exception.User.PasswordWrongException;
import org.example.operator.common.exception.User.UsernameHasBeenRegisteredException;
import org.example.operator.mapper.UserMapper;
import org.example.operator.pojo.dto.User.UserLoginDTO;
import org.example.operator.pojo.entity.User;
import org.example.operator.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpI implements UserLoginService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User login(UserLoginDTO userLoginDTO) {
        //判断用户是否存在
        User user=userMapper.getByUsername(userLoginDTO.getUsername());
        if(user==null){
            throw new UsernameHasBeenRegisteredException(LoginConstant.USER_NAME_NOT_FOUND);
        }
        //判断密码是否正确
        if(!user.getPassword().equals(userLoginDTO.getPassword())){
            throw new PasswordWrongException(LoginConstant.PASSWORD_WRONG);
        }
        return user;
    }
}
