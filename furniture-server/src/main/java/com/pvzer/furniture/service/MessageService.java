package com.pvzer.furniture.service;

import com.pvzer.furniture.pojo.Message;
import com.pvzer.furniture.pojo.MessageInfo;

import java.util.List;

public interface MessageService {
    void add(Message message);
    void delete(Integer id);
    List<MessageInfo> query(Integer page, Integer pageSize);
}
