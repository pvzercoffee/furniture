package com.pvzer.furniture.service.impl;


import com.pvzer.furniture.mapper.UserMapper;
import com.pvzer.furniture.pojo.User;
import com.pvzer.furniture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User signup(User user) {
        return null;
    }
}
