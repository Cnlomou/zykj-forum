package com.zhuoyue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Linmo
 * @create 2020/4/16 18:53
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.zhuoyue.file.dao")
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}
