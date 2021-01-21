package com.shr.service.minio.config;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 9:04
 * @description：SwaggerConfig
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Value("${spring.application.name}")
    private String title;

    @Bean
    public Docket docket(ApiInfo apiInfo)
    {
        return new Docket(DocumentationType.OAS_30).pathMapping("/")
                .enable(true)
                .apiInfo(apiInfo)
                .select() // 指定需要发布到Swagger的接口目录，不支持通配符
                .apis(RequestHandlerSelectors.basePackage("com.shr.service.minio.controller"))
                .paths(PathSelectors.any())
                .build()
                // 支持的通讯协议集合
                .protocols(this.newHashSet("https", "http"));
    }

    @Bean
    public ApiInfo apiInfo()
    {
        return new ApiInfoBuilder().title(title + "接口")
                .version("Application Version: 1.0.0")
                .build();
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts)
    {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return Collections.emptySet();
    }
}