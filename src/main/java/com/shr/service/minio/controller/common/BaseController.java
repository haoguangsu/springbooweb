package com.shr.service.minio.controller.common;

/**
 * @author ：206612
 * @date ：Created in 2020/10/18 15:03
 * @description：BaseController
 */

import com.shr.service.minio.entity.common.ErrorCodeList;
import com.shr.service.minio.entity.common.HttpResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class BaseController extends LogSenderController implements UnitiveController {
    public BaseController()
    {
    }

    @Override
    public <T> HttpResponse<T> ok(T entity)
    {
        HttpResponse<T> httpResponse = new HttpResponse<>();
        httpResponse.setCode(0);
        httpResponse.setType(HttpResponse.Type.OK);
        if (entity instanceof Integer) {
            httpResponse.setData(null);
        }
        else {
            httpResponse.setData(entity);
        }
        return httpResponse;
    }

    @Override
    public <T> HttpResponse<T> ok(Void entity)
    {
        HttpResponse<T> httpResponse = new HttpResponse<>();
        httpResponse.setCode(0);
        httpResponse.setType(HttpResponse.Type.OK);
        httpResponse.setData(null);
        return httpResponse;
    }

    @Override
    public <T> HttpResponse<T> error(T entity)
    {
        HttpResponse<T> httpResponse = new HttpResponse<>();
        httpResponse.setCode(10001);
        httpResponse.setType(HttpResponse.Type.ERROR);
        httpResponse.setData(entity);
        if (entity instanceof String) {
            httpResponse.setMessage((String) entity);
        }

        return httpResponse;
    }

    @Override
    public <T> HttpResponse<T> unauthorized(T entity)
    {
        HttpResponse<T> httpResponse = new HttpResponse<>();
        httpResponse.setCode(10001);
        httpResponse.setType(HttpResponse.Type.UNAUTHORIZED);
        httpResponse.setData(entity);
        return httpResponse;
    }

    @Override
    public <T> HttpResponse<T> redirect(T entity)
    {
        HttpResponse<T> httpResponse = new HttpResponse<>();
        httpResponse.setCode(10001);
        httpResponse.setType(HttpResponse.Type.INTERNAL_ERROR);
        httpResponse.setData(entity);
        return httpResponse;
    }

    @ExceptionHandler(Exception.class)
    public HttpResponse<String> exceptionHandler(Exception e)
    {
        //如果是入参被@Validated校验住的异常
        if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            StringBuilder errorMessage = new StringBuilder(bindingResult.getFieldErrors().size() * 16);
            errorMessage.append("Invalid Request: ");
            for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
                if (i > 0) {
                    errorMessage.append(',');
                }
                FieldError fieldError = bindingResult.getFieldErrors().get(i);
                errorMessage.append(fieldError.getField());
                errorMessage.append(':');
                errorMessage.append(fieldError.getDefaultMessage());
            }
            HttpResponse<String> httpResponse = new HttpResponse<>();
            httpResponse.setCode(10001);
            httpResponse.setMessage(errorMessage.toString());
            return httpResponse;
        }
        else {
            return error(e.getMessage());
        }
    }

    public <T> HttpResponse<T> checkResult(T result)
    {
        if (Objects.isNull(result) || result.equals(0)) {
            return error(result);
        }
        if (result instanceof String && ErrorCodeList.getErrorCodeList().contains(result)) {
            return error(result);
        }
        return ok(result);

    }

}