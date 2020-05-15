package com.zhuoyue.getway.user;

import com.zhuoyue.getway.user.filter.AccessTokenGlobalFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Linmo
 * @create 2020/4/18 15:03
 */
@SpringBootApplication
@EnableEurekaClient
public class UserGetWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserGetWayApplication.class, args);
    }

    /**
     * 添加ip键解析器
     *
     * @return
     */
    @Bean(name = "ipkeyresolver")
    public KeyResolver ipKeyResolver() {
        return new KeyResolver() {
            @Override
            public Mono<String> resolve(ServerWebExchange exchange) {
                String hostString = exchange.getRequest().getRemoteAddress().getHostString();
                return Mono.just(hostString);
            }
        };
    }

    /**
     * 加入令牌过滤器
     * @return
     */
//    @Bean
//    public GlobalFilter accessTokenFilter(){
//        return new AccessTokenGlobalFilter();
//    }
}
