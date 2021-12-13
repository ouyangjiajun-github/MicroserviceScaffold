package com.ouyang.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//使服务可以注册到其他服务发现组件中，与@EnableEurekaClient的区别是@EnableEurekaClient只能用于eureka
@EnableDiscoveryClient
/**开启zuul注解 @EnableZuulProxy简单理解为@EnableZuulServer的增强版，
*   当Zuul与Eureka、Ribbon等组件配合使用时，我们使用@EnableZuulProxy。
*/
@EnableZuulProxy
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
