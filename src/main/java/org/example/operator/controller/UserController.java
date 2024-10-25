package org.example.operator.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.operator.common.exception.User.PasswordWrongException;
import org.example.operator.common.exception.User.UsernameHasBeenRegisteredException;
import org.example.operator.common.result.Result;
import org.example.operator.common.utils.JwtUtils;
import org.example.operator.pojo.dto.User.UserLoginDTO;
import org.example.operator.pojo.dto.User.UserRegisterDTO;
import org.example.operator.pojo.entity.User;
import org.example.operator.pojo.vo.UserLoginVO;
import org.example.operator.properties.JwtProperties;
import org.example.operator.service.UserLoginService;
import org.example.operator.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("Operator/user")
public class UserController {
    @Autowired
    private UserRegisterService userRegisterService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private UserLoginService userLoginService;
    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping("/register")
    public Result<String> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        log.info("用户注册信息：{}", userRegisterDTO);
        try {
            userRegisterService.register(userRegisterDTO);
            return Result.success("注册成功");
        }
        catch (UsernameHasBeenRegisteredException e) {
            log.error("用户名已被注册");
            return Result.error("用户名已被注册");
        }

    }

    @RequestMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录信息：{}", userLoginDTO);
        try {
            User user = userLoginService.login(userLoginDTO);
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getId());
            claims.put("username", user.getUsername());
            claims.put("password", user.getPassword());
            String token=jwtUtils.createJwt(claims);
            UserLoginVO userLoginVO=UserLoginVO.builder()
                    .id(user.getId())
                    .token(token)
                    .build();
            return Result.success("登录成功",userLoginVO);
        }
        catch (UsernameHasBeenRegisteredException e)
        {
            log.error("用户名不存在");
            return Result.error("用户名不存在");
        }
        catch (PasswordWrongException e)
        {
            log.error("密码错误");
            return Result.error("密码错误");
        }

    }
}
