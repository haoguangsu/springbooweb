package com.shr.service.minio.service.common;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 15:06
 * @description：LogServiceImpl
 */

import com.alibaba.fastjson.JSONObject;
import com.shr.service.minio.config.RabbitMQConfig;
import com.shr.service.minio.entity.common.LoggerEntity;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service("LogService")
public class LogServiceImpl {

    @Autowired
    private AmqpTemplate template;

    public void sendLog(LoggerEntity log)
    {
        log.setDateTime(new Date());

        template.convertAndSend(RabbitMQConfig.LOGGER_QUEUE,
                new String(JSONObject.toJSONString(log).getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8));
    }
}