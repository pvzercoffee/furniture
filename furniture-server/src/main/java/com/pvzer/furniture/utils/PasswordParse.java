package com.pvzer.furniture.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordParse {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String createPassword(String rawPwd)
    {
        return passwordEncoder.encode(rawPwd);
    }

    public boolean matches(String rawPwd,String encodedPwd)
    {
        return passwordEncoder.matches(rawPwd,encodedPwd);
    }
}
