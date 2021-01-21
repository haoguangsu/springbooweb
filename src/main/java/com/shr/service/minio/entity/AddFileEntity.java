package com.shr.service.minio.entity;

import lombok.Data;

/**
 * @author ：206612
 * @date ：Created in 2021/1/19 16:00
 * @description：AddFileEntity
 */
@Data
public class AddFileEntity {
    private String bucket;
    private String fileName;
    private String suffix;
    private byte[] bytes;
}
