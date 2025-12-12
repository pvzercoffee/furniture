package com.pvzer.furniture.exception;

import com.pvzer.furniture.pojo.Result;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GolbalExceptionHandler {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class ExceptionContext{
        private String error;
        private String result;
    }

    @ExceptionHandler(Exception.class)
    public Result handlerException(Exception e){

        List<ExceptionContext> exceptionSet = new LinkedList<>();
        exceptionSet.add(new ExceptionContext("for key 'username'","这个用户名已经被使用了，换一个吧"));
        exceptionSet.add(new ExceptionContext("for key 'email'", "该邮箱已被注册"));
        exceptionSet.add(new ExceptionContext("for key 'telephone'", "手机号已被注册"));
        exceptionSet.add(new ExceptionContext("because \"result\" is null", "用户名或密码错误"));
        exceptionSet.add(new ExceptionContext("删除失败", "删除失败"));

        for(ExceptionContext context : exceptionSet){
            if(e.getMessage().contains(context.getError())){
                System.out.println("来自全局异常的输出："+context.getResult());
                return Result.error(context.getResult());
            }
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
