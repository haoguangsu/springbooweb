package com.shr.service.minio.util.common;

import com.shr.service.minio.controller.common.LogSenderController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 15:12
 * @description：CommonUtil
 */
@Component
@Slf4j
public class CommonUtil extends LogSenderController {
    protected final static Map<String, String> defectMap = new HashMap();

    static {
        defectMap.put("bj", "表计");
        defectMap.put("bj_bpmh", "表盘模糊");
    }

    public String getFileNameEncode(String fileName)
    {
        char[] ch = fileName.toCharArray();
        String result = "";
        for (int i = 0; i < ch.length; i++) {
            char temp = ch[i];
            if (isChinese(temp)) {
                try {
                    String encode = URLEncoder.encode(String.valueOf(temp), StandardCharsets.UTF_8.name());
                    result = result + encode;
                } catch (UnsupportedEncodingException e) {
                    sendErrorLog("CommonUtil getFileNameEncode error", e.getMessage());
                }
            }
            else {
                result = result + temp;
            }
        }
        return result;
    }

    /**
     * 判断字符是否为汉字
     *
     * @param c
     * @return
     */
    public boolean isChinese(char c)
    {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION;
    }

    public boolean checkBucketName(String bucket)
    {
        if (StringUtils.isEmpty(bucket)) {
            return false;
        }
        try {
            String key = "[A-Za-z0-9.-]{3,}$";
            return bucket.matches(key);
        } catch (Exception e) {
            return false;
        }
    }
}
