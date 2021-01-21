package com.shr.service.minio.entity;

import lombok.Data;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 15:44
 * @description：MinioEntity
 */
@Data
public class MinioEntity {
    private String bucket;
    private String fileName;
    private String path;
    private String creationDate;
    private Boolean isFile;
    private long size;
}
