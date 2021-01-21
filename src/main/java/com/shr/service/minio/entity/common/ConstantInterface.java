package com.shr.service.minio.entity.common;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 15:28
 * @description：ConstantInterface
 */
public class ConstantInterface {
    public static final String SPACE_SEPARATOR = " ";
    public static final String SLASH_SEPARATOR = "/";
    public static final String BUCKET_POLICY = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\"," +
            "\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\",\"s3:ListBucket\"],\"Resource\":[\"arn:aws:s3:::bucketname\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetObject\"],\"Resource\":[\"arn:aws:s3:::bucketname/*\"]}]}";

    public static final String OK = "OK";
}
