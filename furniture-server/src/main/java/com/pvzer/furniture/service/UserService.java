package com.pvzer.furniture.service;

import com.pvzer.furniture.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User signup(User user);
}
