package com.shr.service.minio.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 16:28
 * @description：ReturnTemplateEntity
 */
@Data
public class ReturnTemplateEntity {
    private String title;
    private String path;
    private List<ReturnTemplateEntity> children;
}
