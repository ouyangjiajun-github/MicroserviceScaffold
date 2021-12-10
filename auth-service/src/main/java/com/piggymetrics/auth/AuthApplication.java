package com.piggymetrics.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
//@EnableResourceServer是为OAuth2资源服务器提供方便的注释，启用Spring Security 过滤器，通过传入的OAuth2令牌对请求进行身份验证。@EnableResourceServer导入了ResourceServerConfiguration配置类，该配置类继承了WebSecurityConfigurerAdapter，拥有了http security的相关能力。
@EnableResourceServer
@EnableDiscoveryClient
//开启基于方法的安全认证机制 使@PreAuthorize生效
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
