package com.pvzer.furniture.service.impl;


import com.pvzer.furniture.config.SecurityConfig;
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

    @Autowired
    SecurityConfig securityConfig;

    @Override
    public void signup(User user) {

        String encodedPwd = securityConfig.passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPwd);
        userMapper.signup(user);

    }

    @Override
    public User login(User user) {


        User result =  userMapper.login(user);

        if(result != null && securityConfig.passwordEncoder().matches(user.getPassword(),result.getPassword()))
        {
            result.setPassword(null);
            return result;
        }

        return null;
    }
}
