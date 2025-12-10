package com.pvzer.furniture.service.impl;

import com.github.pagehelper.PageHelper;
import com.pvzer.furniture.mapper.MessageMapper;
import com.pvzer.furniture.pojo.Message;
import com.pvzer.furniture.pojo.MessageInfo;
import com.pvzer.furniture.pojo.MessageQueryInfo;
import com.pvzer.furniture.service.MessageService;
import com.pvzer.furniture.utils.CurrentHolder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    //添加留言
    @Transactional
    @Override
    public void add(Message message) {
        //获取发表账号的id
        message.setUserId(CurrentHolder.getCurrentId());
        //先把留言插入messages表并获取回显的主键
        messageMapper.addToMessages(message);
        Integer messageId = message.getId();
        //动态sql以itemList为索引实现多对多关系插入到message_items_link
        messageMapper.addToMessageItemsLink(messageId,message.getItemList());
    }

    //查询留言
    @Override
    public Map<String,Object> query(Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);

        //查出留言列表
        List<MessageInfo> messageList = messageMapper.query();

        //查询总记录数
        Integer total = messageMapper.queryNum();

        //封装所有数据到Map
        Map<String,Object> result = new HashMap<>();
        result.put("total",total);
        result.put("messages",messageList);
        return result;
    }

    //删除留言
    @Override
    @Transactional
    public void delete(Integer id) {

        messageMapper.deleteLink(id);
        messageMapper.delete(id);
    }

    //查询可用项目
    @Override
    public List<Map<Integer,String>> queryItem() {
        return messageMapper.queryItem();
    }
}
