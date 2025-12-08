package com.pvzer.furniture.exception;

import com.pvzer.furniture.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GolbalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){
        System.out.println(e.getMessage());
        return Result.error("出错了，请联系管理员");
    }

    @ExceptionHandler(SelectErrorException.class)
    public Result handlerSelectErrorException(){
        return Result.error("失败，您的输入错误");
    }
}
