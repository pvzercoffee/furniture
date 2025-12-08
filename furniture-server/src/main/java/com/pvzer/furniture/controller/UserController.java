package com.pvzer.furniture.controller;

import com.pvzer.furniture.pojo.LoginInfo;
import com.pvzer.furniture.pojo.Result;
import com.pvzer.furniture.pojo.User;
import com.pvzer.furniture.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/signup")
    public Result signup(@RequestBody User user){
        userServiceImpl.signup(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        LoginInfo data = userServiceImpl.login(user);

        return Result.success(data);
    }
}
