package com.shr.service.minio.controller.common;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 15:05
 * @description：LogSenderController
 */

import com.shr.service.minio.entity.common.LoggerEntity;
import com.shr.service.minio.service.common.LogServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Component
public class LogSenderController {
    private static final int NUM_TWO = 2;
    @Resource
    private LogServiceImpl logService;

    private void sendLog(StackTraceElement[] stackTraceElement, LoggerEntity.LogEnum logEnum, String logText)
    {
        if (Objects.isNull(stackTraceElement) || Objects.isNull(stackTraceElement[NUM_TWO])) {
            LoggerEntity loggerEntity = LoggerEntity.builder().logText("There has error").build();
            logService.sendLog(loggerEntity);
            return;
        }
        LoggerEntity.LoggerEntityBuilder loggerEntityBuilder = LoggerEntity.builder().clazzName(stackTraceElement[NUM_TWO].getClassName());
        LoggerEntity loggerEntity = loggerEntityBuilder
                .methodName(stackTraceElement[NUM_TWO].getMethodName())
                .dateTime(new Date())
                .serviceName("INSPECTSERVICE")
                .lineNum(stackTraceElement[NUM_TWO].getLineNumber())
                .logRank(logEnum)
                .logText(logText)
                .build();
        logService.sendLog(loggerEntity);

    }

    public void sendInfoLog(String s, String s2)
    {
        String logInfo = s + s2;
        sendLog(Thread.currentThread().getStackTrace(), LoggerEntity.LogEnum.INFO, logInfo);
    }

    public void sendDebugLog(String s, String s2)
    {
        String logInfo = s + s2;
        sendLog(Thread.currentThread().getStackTrace(), LoggerEntity.LogEnum.DEBUG, logInfo);
    }

    public void sendErrorLog(String s, String s2)
    {
        String logInfo = s + s2;
        sendLog(Thread.currentThread().getStackTrace(), LoggerEntity.LogEnum.ERROR, logInfo);
    }
}