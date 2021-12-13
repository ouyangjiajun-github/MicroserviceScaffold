package com.ouyang.hystrixservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**EnableCircuitBreaker开启断路器功能
 * 主要初始化了四大点功能：
 * 1、服务降级，使用@HystrixCommand(fallbackMethod = "getDefaultUser")，当服务无法响应时调用getDefaultUser充当降级服务返回
 * 2、ignoreExceptions 捕捉不同类型的异常进行降级
 * 3、CacheResult 使用缓存，（在缓存使用过程中，我们需要在每次使用缓存的请求前后对HystrixRequestContext进行初始化和关闭，）
 * 4、请求合并 微服务系统中的服务间通信，需要通过远程调用来实现，随着调用次数越来越多，占用线程资源也会越来越多。Hystrix中提供了@HystrixCollapser用于合并请求，从而达到减少通信消耗及线程数量的效果。
 *
 *
 *
 *
 *
 * */
@EnableCircuitBreaker
@SpringBootApplication
@EnableDiscoveryClient
public class HystrixServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixServiceApplication.class, args);
    }

}
