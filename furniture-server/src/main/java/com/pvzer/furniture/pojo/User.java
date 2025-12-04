package com.pvzer.furniture.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private Integer status;
    private Integer role;
    private String username;
    private String password;
    private String email;
    private String name;
    private String telephone;
    private Integer gender;
    private LocalDate birthday;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private LocalDateTime lastLogin;
}
