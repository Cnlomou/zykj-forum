package com.zhuoyue;


import entity.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author Linmo
 * @create 2020/4/16 13:26
 */
@SpringBootApplication
@MapperScan(basePackages = "com.zhuoyue.article.dao")
@EnableFeignClients(basePackages = "com.zhuoyue.user.feign")
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(0, 0);
    }
}
