package com.zhuoyue.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Linmo
 * @create 2020/4/23 16:59
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients(basePackages = {"com.zhuoyue.article.feign", "com.zhuoyue.user.feign", "com.zhuoyue.search.feign"})
@EnableEurekaClient
@EnableAsync
public class WebPageApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebPageApplication.class, args);
    }
}
