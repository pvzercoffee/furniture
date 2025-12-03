package com.pvzer.furniture.controller;

import com.pvzer.furniture.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Users{

    @GetMapping("/hello")
    public Result hello(){
        return Result.success();
    }
}
