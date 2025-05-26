package com.example.user_service.config;

import com.example.user_service.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

//    @Bean
//    public FilterRegistrationBean<JwtFilter> jwtFilter(JwtFilter filter) {
//        FilterRegistrationBean<JwtFilter> reg = new FilterRegistrationBean<>();
//        reg.setFilter(filter);
//        reg.addUrlPatterns("/*"); // all endpoints
//        return reg;
//    }
}
