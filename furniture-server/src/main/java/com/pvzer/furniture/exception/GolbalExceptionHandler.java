package com.pvzer.furniture.exception;

import com.pvzer.furniture.pojo.Result;
import io.jsonwebtoken.JwtException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GolbalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){

        System.out.println("来自全局异常的输出："+e.getMessage());

        if(e.getMessage().contains("for key 'username'")) {
            return Result.error("这个用户名已经被使用了，换一个吧");
        }
        if(e.getMessage().contains("for key 'email'")) {
            return Result.error("该邮箱已被注册");
        }
        if(e.getMessage().contains("for key 'telephone'")) {
            return Result.error("手机号已被注册");
        }
        if(e.getMessage().contains("because \"result\" is null")){
            return Result.error("用户名或密码错误");
        }

        return Result.error("出错了，请联系管理员");
    }

    @ExceptionHandler(SelectErrorException.class)
    public Result handlerSelectErrorException(){
        return Result.error("失败，您的输入错误");
    }

    @ExceptionHandler(JwtException.class)
    public Result handlerJwtParseException(){
        return Result.error("登录信息已过期，请重新登录");
    }
}
