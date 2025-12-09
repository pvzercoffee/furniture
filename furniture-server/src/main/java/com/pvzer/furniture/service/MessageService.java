package com.pvzer.furniture.service;

import com.pvzer.furniture.pojo.Message;

public interface MessageService {
    String add(Message message);
    String delete(Integer id);
    String query();
}
