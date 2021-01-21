package com.shr.service.minio.entity.common;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 15:04
 * @description：HttpResponse
 */
import lombok.Data;

@Data
public class HttpResponse<T> {
    private int code;
    private Type type;
    private T data;
    private String message;

    public enum Type {
        OK, ERROR, UNAUTHORIZED, INTERNAL_ERROR
    }
}