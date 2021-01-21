package com.shr.service.minio.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 9:01
 * @description：MqConfig
 */
@Configuration
public class RabbitMQConfig {
    public static final String LOGGER_QUEUE = "logger.queue";

    @Bean
    public Queue loggerQueue()
    {
        return new Queue(LOGGER_QUEUE);
    }

}
