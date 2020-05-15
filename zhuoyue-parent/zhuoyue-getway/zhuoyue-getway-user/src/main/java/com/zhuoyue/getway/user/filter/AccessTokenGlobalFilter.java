package com.zhuoyue.getway.user.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Linmo
 * @create 2020/4/18 17:07
 */
public class AccessTokenGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        String auth = "Authorization";

        //从请求头中获取
        String authorization = request.getHeaders().getFirst(auth);

        //从cookie头中获取
        if (StringUtils.isEmpty(authorization)) {
            HttpCookie first = request.getCookies().getFirst(auth);
            authorization = first == null ? null : first.getValue();
        }

        //从请求头中获取
        if (StringUtils.isEmpty(authorization)) {
            authorization = request.getQueryParams().getFirst(auth);
        }

        //将令牌放到请求头
        if (!StringUtils.isEmpty(authorization)) {
            request.mutate().header(auth, authorization);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
