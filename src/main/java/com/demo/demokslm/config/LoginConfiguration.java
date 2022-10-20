package com.demo.demokslm.config;

import com.demo.demokslm.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Gengyu Liu(xmulgy@126.com)
 * @description: 拦截器配置
 * @date 2022/10/15 21:37
 */
@Configuration
public class LoginConfiguration implements WebMvcConfigurer {
    @Bean
    LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login","/register","/error");
    }
}
