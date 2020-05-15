package com.changyue.oauth.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Linmo
 * @create 2020/4/21 15:58
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result excetion(Exception e) {
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
