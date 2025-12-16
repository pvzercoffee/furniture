package com.pvzer.furniture.service.impl;


import com.pvzer.furniture.config.SecurityConfig;
import com.pvzer.furniture.exception.JwtParseException;
import com.pvzer.furniture.exception.SelectErrorException;
import com.pvzer.furniture.mapper.MessageMapper;
import com.pvzer.furniture.mapper.UserMapper;
import com.pvzer.furniture.pojo.LoginInfo;
import com.pvzer.furniture.pojo.User;
import com.pvzer.furniture.service.UserService;
import com.pvzer.furniture.utils.CurrentHolder;
import com.pvzer.furniture.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private SecurityConfig securityConfig;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //注册业务
    @Override
    public void signup(User user) {

        String encodedPwd = securityConfig.passwordEncoder().encode(user.getPassword());
        user.setPassword(encodedPwd);
        userMapper.signup(user);
    }

    @Override
    public LoginInfo login(User user) {

        //LoginInfo不下发密码，因此查询结果用User封装
        User result =  userMapper.login(user);

        System.out.println(securityConfig.passwordEncoder().matches(user.getPassword(),result.getPassword()));

        //密码错误抛异常
        if(!securityConfig.passwordEncoder().matches(user.getPassword(),result.getPassword()))
        {
            throw new SelectErrorException();
        }

        //密码正确的情况下封装LoginInfo
        LoginInfo loginInfo = new LoginInfo();

        loginInfo.setEmail(result.getEmail());
        loginInfo.setName(result.getName());
        loginInfo.setGender(result.getGender());
        loginInfo.setTelephone(result.getTelephone());
        loginInfo.setUsername(result.getUsername());
        loginInfo.setBirthday(result.getBirthday());

        //把id作为令牌载荷
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",result.getId());

        //创建令牌并传给LoginInfo
        loginInfo.setToken(JwtUtils.generateToken(claims));
        //登陆成功会返回信息和和令牌，不成功则是一个null
        return loginInfo;
    }

    //查询个人信息业务
    @Override
    public LoginInfo me(String token) {

        //根据token返回用户信息
        Claims claims = JwtUtils.parseToken(token);
        Integer id = (Integer) claims.get("id");

        return userMapper.me(id);
    }

    //修改信息业务
    @Override
    public void modify(User user) {
        //确保id一致
        user.setId(CurrentHolder.getCurrentId());
        //给密码加密
        if(user.getPassword() != null){
            String encodeingPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodeingPassword);
        }

        userMapper.modify(user);
    }

    //账号注销业务
    @Override
    @Transactional
    public void destroy() {
        Integer id = CurrentHolder.getCurrentId();
        List<Integer> messageIdList = messageMapper.queryIdByUserId(id);
        if(!messageIdList.isEmpty()){

            messageMapper.destroyMessageItemsLink(messageIdList);
            messageMapper.destroyMessageById(id);
        }
        userMapper.destroyById(id);
    }
}
