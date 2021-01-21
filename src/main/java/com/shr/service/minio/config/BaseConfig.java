package com.shr.service.minio.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 9:00
 * @description：BaseConfig
 */
@Configuration
@EnableAutoConfiguration(exclude = {FreeMarkerAutoConfiguration.class})
public class BaseConfig {
}
