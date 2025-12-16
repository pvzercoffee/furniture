package com.pvzer.furniture.service;

import com.pvzer.furniture.pojo.LoginInfo;
import com.pvzer.furniture.pojo.User;
import org.springframework.stereotype.Service;


public interface UserService {

    void signup(User user);
    LoginInfo login(User user);
    LoginInfo me(String token);
    void modify(User user);
    void destroy();
}
