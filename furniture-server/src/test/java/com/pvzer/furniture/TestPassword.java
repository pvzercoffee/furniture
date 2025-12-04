package com.pvzer.furniture;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


public class TestPassword {
    @Test
    public void bcryptTest(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = "123456ABC";

        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("加密后的密码："+encodedPassword);

        String testPassword = "123456AB.";

        boolean matches = passwordEncoder.matches(testPassword,encodedPassword);

        System.out.println(matches ? "输入正确" : "输入错误");

    }
}
