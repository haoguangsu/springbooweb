package com.shr.service.minio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 8:41
 * @description：MinioApplication
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableScheduling
@EnableFeignClients
public class MinioApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(MinioApplication.class, args);
    }
}
