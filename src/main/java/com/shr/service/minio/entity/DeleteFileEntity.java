package com.shr.service.minio.entity;

import lombok.Data;

import java.util.List;

/**
 * @author ：206612
 * @date ：Created in 2021/1/19 16:10
 * @description：DeleteFileEntity
 */
@Data
public class DeleteFileEntity {
    private String bucket;
    private List<String> fileNames;
}
