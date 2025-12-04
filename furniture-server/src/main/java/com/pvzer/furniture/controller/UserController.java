package com.pvzer.furniture.controller;

import com.pvzer.furniture.pojo.Result;
import com.pvzer.furniture.pojo.User;
import com.pvzer.furniture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/signup")
    public Result login(@RequestBody User user){
        userService.signup(user);
        return Result.success();
    }
}
