package com.zhuoyue.user.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Linmo
 * @create 2020/4/17 13:06
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandle(Exception e) {
        return new Result<>(false, StatusCode.ERROR, "发生错误", e.getMessage());
    }
}
