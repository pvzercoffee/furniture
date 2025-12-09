package com.pvzer.furniture.service.impl;

import com.pvzer.furniture.mapper.MessageMapper;
import com.pvzer.furniture.pojo.Message;
import com.pvzer.furniture.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Transactional
    @Override
    public String add(Message message) {
        //先把留言插入messages表并获取回显的主键
        messageMapper.addToMessages(message);
        Integer messageId = message.getId();
        //动态sql以itemList为索引实现多对多关系插入到message_items_link
        messageMapper.addToMessageItemsLink(messageId,message.getItemList());

        return "发表成功";
    }

    @Override
    public String delete(Integer id) {
        return "";
    }

    @Override
    public String query() {
        return "";
    }
}
