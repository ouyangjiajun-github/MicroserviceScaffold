package com.ouyang.micro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author ouyangjaijun
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();// 默认启动csrf保护，此处禁用
        http.authorizeRequests()
                //任何人都可以访问这个路径 健康检查的路径
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()//其他的路径都需要进行用户验证
                .and()
                .httpBasic();//开启httpBasic
    }
}
