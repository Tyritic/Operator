package org.example.operator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.operator.common.exception.User.UsernameHasBeenRegisteredException;
import org.example.operator.mapper.UserMapper;
import org.example.operator.pojo.dto.User.UserRegisterDTO;
import org.example.operator.pojo.entity.User;
import org.example.operator.service.UserRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserRegisterServiceImpl implements UserRegisterService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        User tempUser=userMapper.getByUsername(userRegisterDTO.getUsername());
        if(tempUser!=null){
            log.error("用户{}已存在",userRegisterDTO.getUsername());
            throw new UsernameHasBeenRegisteredException("用户名已存在");
        }
        User user=new User();
        BeanUtils.copyProperties(userRegisterDTO,user);
        userMapper.register(user);
        log.info("用户{}注册成功",userRegisterDTO.getUsername());
    }
}
