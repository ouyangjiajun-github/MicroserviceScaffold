package com.ouyang.eruekasecurityserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EruekaSecurityServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EruekaSecurityServerApplication.class, args);
    }

}
