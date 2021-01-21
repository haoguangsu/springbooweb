package com.shr.service.minio.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ：206612
 * @date ：Created in 2020/11/18 11:04
 * @description：MinioConfig
 */
@Component
@ConfigurationProperties(prefix = "spring.minio")
public class MinioConfig {
    private String endPoint;
    private String port;
    private String accessKey;
    private String secretKey;

    public String getEndPoint()
    {
        return endPoint;
    }

    public void setEndPoint(String endPoint)
    {
        this.endPoint = endPoint;
    }

    public String getPort()
    {
        return port;
    }

    public void setPort(String port)
    {
        this.port = port;
    }

    public String getAccessKey()
    {
        return accessKey;
    }

    public void setAccessKey(String accessKey)
    {
        this.accessKey = accessKey;
    }

    public String getSecretKey()
    {
        return secretKey;
    }

    public void setSecretKey(String secretKey)
    {
        this.secretKey = secretKey;
    }
}
