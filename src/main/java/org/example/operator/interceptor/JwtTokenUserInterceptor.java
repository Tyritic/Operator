package org.example.operator.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.example.operator.common.context.BaseContext;
import org.example.operator.properties.JwtProperties;
import org.example.operator.common.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
@Component
@Slf4j
public class JwtTokenUserInterceptor implements HandlerInterceptor {
    @Autowired
    JwtProperties jwtProperties;
    @Autowired
    JwtUtils jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断当前拦截到的是Controller的方法还是其他资源
        // 如果不是HandlerMethod类型，直接放行
        if (!(handler instanceof HandlerMethod)) { //
            return true;
        }

        // 从请求头中获取令牌
        String token = request.getHeader(jwtProperties.getUserTokenName());
        // 校验令牌
        try {
            log.info("jwt校验:{}", token);
            Claims claims=jwtUtil.parseJwt(token);
            // 为线程局部变量设置id
            BaseContext.setCurrentMap(claims);
            return true;
        } catch (Exception ex) {
            // 令牌解析的时候遇到异常，不通过，返回401状态码
            response.setStatus(401);
            return true;
        }
    }
}
