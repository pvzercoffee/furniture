package com.pvzer.furniture.controller;

import com.pvzer.furniture.pojo.LoginInfo;
import com.pvzer.furniture.pojo.Result;
import com.pvzer.furniture.pojo.User;
import com.pvzer.furniture.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")  // 允许所有跨域请求
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    //注册
    @PostMapping("/signup")
    public Result signup(@RequestBody User user){
        userServiceImpl.signup(user);
        return Result.success();
    }

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        LoginInfo data = userServiceImpl.login(user);
        return Result.success(data);
    }

    //查询个人信息
    @PostMapping("/me")
    public Result me(@RequestHeader String token){
        LoginInfo loginInfo = userServiceImpl.me(token);
        return Result.success(loginInfo);
    }

    //个人信息修改
    @PutMapping("/user")
    public Result modify(@RequestBody User modifyUser){
        userServiceImpl.modify(modifyUser);
        return Result.success("修改成功",null);
    }

    //账号注销
    @DeleteMapping("/user")
    public Result destroy(){
        userServiceImpl.destroy();
        return Result.success();
    }
}
