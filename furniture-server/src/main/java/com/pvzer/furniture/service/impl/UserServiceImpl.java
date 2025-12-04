package com.pvzer.furniture.service.impl;


import com.pvzer.furniture.mapper.UserMapper;
import com.pvzer.furniture.pojo.User;
import com.pvzer.furniture.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public void signup(User user) {



        userMapper.signup(user);

    }
}
