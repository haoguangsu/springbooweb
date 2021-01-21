package com.shr.service.minio.entity.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：206612
 * @date ：Created in 2020/12/5 16:25
 * @description：ErrorCode 错误码
 * 10001-11000
 */
public class ErrorCodeList {
    public static final String NAME_EXIST = "名称已存在。";
    public static final String NAME_ERROR = "名称无效。小写字母、句点、连字符、数字是唯一允许的字符，长度至少应为3个字符。";
    public static final String BRIEF_IS_NOT_JSON = "brief不是json格式。";
    public static final String RECOGNITION_TYPE_IS_NULL = "识别类型为空。";
    public static final String TASK_TYPE_IN_USE = "任务类型正在使用中，无法删除。";
    public static final String NO_INSPECT_POINT = "当前没有巡视点";

    private final static List<String> ErrorCodeList = new ArrayList<>();

    static {
        ErrorCodeList.add(NAME_EXIST);
        ErrorCodeList.add(NAME_ERROR);
        ErrorCodeList.add(BRIEF_IS_NOT_JSON);
        ErrorCodeList.add(RECOGNITION_TYPE_IS_NULL);
        ErrorCodeList.add(TASK_TYPE_IN_USE);
        ErrorCodeList.add(NO_INSPECT_POINT);
    }

    private ErrorCodeList()
    {

    }

    public static List<String> getErrorCodeList()
    {
        return ErrorCodeList;
    }
}
