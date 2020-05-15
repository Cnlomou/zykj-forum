package com.zhuoyue.user.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Linmo
 * @create 2020/4/17 12:57
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
                .apis(RequestHandlerSelectors.basePackage("com.zhuoyue.user.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("用户微服务")
                //描述
                .description("API 描述")
                .build();
    }
}
