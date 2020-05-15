package com.zhuoyue.file.conf;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Linmo
 * @create 2020/4/17 10:41
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {


    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SPRING_WEB)//docket 的类型
                .apiInfo(apiInfo())
                .select()
                //选择api的包
                .apis(requestHandlerPredicate())
                .paths(PathSelectors.any())
                .build();
    }

    private Predicate<RequestHandler> requestHandlerPredicate() {
        return Predicates.or(new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler requestHandler) {
                return requestHandler.declaringClass().getName().startsWith("com.zhuoyue.file.controller");
            }
        }, new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler requestHandler) {
                String name = requestHandler.declaringClass().getName();
                int i = name.lastIndexOf('.');
                return name.substring(0, i).equals("entity");
            }
        });
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("文件上下传微服务")
                //描述
                .description("API 描述")
                .build();
    }
}
