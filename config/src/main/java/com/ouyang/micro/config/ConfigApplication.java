package com.ouyang.micro.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 实现了一个从本地读取配置的配置中心，springcloud config只需要引入spring-cloud-config-server 依赖，
 * 则可以将所有实现类的事情交由依赖完成，我们只需要在启动类上配置注解@EnableConfigServer即可实现配置中心功能
 *
 * @author ouyangjaijun
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }

}
