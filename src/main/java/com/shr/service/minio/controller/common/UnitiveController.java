package com.shr.service.minio.controller.common;

import com.shr.service.minio.entity.common.HttpResponse;

/**
 * @author ：206612
 * @date ：Created in 2021/1/15 15:03
 * @description：UnitiveController
 */

public interface UnitiveController {

    <T> HttpResponse<T> ok(T t);

    <T> HttpResponse<T> ok(Void t);

    <T> HttpResponse<T> error(T t);

    <T> HttpResponse<T> unauthorized(T t);

    <T> HttpResponse<T> redirect(T t);
}
