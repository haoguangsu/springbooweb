package com.shr.service.minio;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 8:51
 * @description：SchedulerTask
 */
@Component
public class SchedulerTask  {

    @Autowired
    private Test test;

    @Scheduled(cron = "0/5 * * * * ?") // 每5秒运行
    public void startScanTask() throws JsonProcessingException
    {
        try {
//            test.test();
        } catch (Exception e) {

            throw e;
        }
    }
}