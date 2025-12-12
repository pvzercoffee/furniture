package com.pvzer.furniture.service;

import com.pvzer.furniture.pojo.Message;
import com.pvzer.furniture.pojo.MessageInfo;

import java.util.List;
import java.util.Map;

public interface MessageService {
    void add(Message message);
    Integer delete(Integer id);
    Map<String,Object> queryAll(Integer page, Integer pageSize);
    List<Map<Integer,String>> queryItem();
    Map<String,Object> queryByUsername(String username,Integer page, Integer pageSize);
}
