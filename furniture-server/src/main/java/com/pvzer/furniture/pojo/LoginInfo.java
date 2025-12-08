package com.pvzer.furniture.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
    private Integer id;
    private String username;
    private String email;
    private String name;
    private String telephone;
    private Integer gender;
    private String token;
}
