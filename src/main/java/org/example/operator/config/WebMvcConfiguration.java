package org.example.operator.config;


import lombok.extern.slf4j.Slf4j;
import org.example.operator.interceptor.JwtTokenUserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    JwtTokenUserInterceptor jwtTokenUserInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("开始注册拦截器");
        log.info("注册Jwt令牌拦截器：{}",jwtTokenUserInterceptor);
        registry.addInterceptor(jwtTokenUserInterceptor)
                .addPathPatterns("/Operator/**")
                .excludePathPatterns("/Operator/user/login")
                .excludePathPatterns("/Operator/user/register");
    }
}
