package com.yiyang.config;

import com.yiyang.exception.BizException;
import com.yiyang.pojo.vo.Result;
import com.yiyang.utils.BizCode;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 全局异常
 */
// @RestControllerAdvice 是 Spring 框架中一个用于统一处理控制器异常和返回结果的注解。
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Map<String, String>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> map = new HashMap<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            map.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return Result.fail(BizCode.DATA_VALID_ERROR, map);
    }

    @ExceptionHandler(BizException.class)
    public Result<String> handlerBizException(BizException e) {
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handlerException(Exception e) {
        return Result.fail(BizCode.FAIL, e.getMessage());
    }
}
