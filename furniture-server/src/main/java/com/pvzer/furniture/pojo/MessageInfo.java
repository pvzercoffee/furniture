package com.pvzer.furniture.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageInfo {
    private Integer id;
    private String username;
    private String name;
    private String telephone;
    private String email;
    private String text;
    private LocalDateTime createTime;
    private List<String> itemList;
}
