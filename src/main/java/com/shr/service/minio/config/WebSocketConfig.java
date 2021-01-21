package com.shr.service.minio.config;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 9:07
 * @description：WebSocketConfig
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter()
    {
        return new ServerEndpointExporter();
    }
}
