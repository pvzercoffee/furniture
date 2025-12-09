package com.pvzer.furniture.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
    private Integer id;
    private String username;
    private String email;
    private String name;
    private String telephone;
    private LocalDate birthday;
    private Integer gender;
    private String token;
}
