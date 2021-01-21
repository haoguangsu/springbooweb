package com.shr.service.minio.entity.common;

/**
 * @author ：206612
 * @date ：Created in 2020/10/18 15:03
 * @description ：LoggerEntity 日志对象
 */

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LoggerEntity {
    //日志级别
    private LogEnum logRank;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateTime;

    private String logText;

    // 服务名
    private String serviceName;

    // 类名
    private String clazzName;

    // 方法名
    private String methodName;

    // 当前行号
    private int lineNum;


    public enum LogEnum {
        DEBUG(1), INFO(2), WARN(3), ERROR(4);

        private final int val;

        LogEnum(int val)
        {
            this.val = val;
        }

        public int getVal()
        {
            return this.val;
        }
    }
}
