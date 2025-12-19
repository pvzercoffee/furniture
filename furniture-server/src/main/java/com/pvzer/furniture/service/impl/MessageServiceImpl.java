package com.pvzer.furniture.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pvzer.furniture.mapper.ItemsMapper;
import com.pvzer.furniture.mapper.MessageItemsLink;
import com.pvzer.furniture.mapper.MessageMapper;
import com.pvzer.furniture.pojo.Message;
import com.pvzer.furniture.pojo.MessageInfo;
import com.pvzer.furniture.service.MessageService;
import com.pvzer.furniture.utils.CurrentHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    MessageItemsLink messageItemsLink;

    @Autowired
    ItemsMapper itemsMapper;
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
        messageItemsLink.addToMessageItemsLink(messageId,message.getItemList());
    }

    //公共方法，通过查询到的留言返回获取total和，和每条留言的item，并封装好结果
    public Map<String,Object> queryResult(List<MessageInfo> messageList){
        //获取每条留言的item
        for(MessageInfo msg : messageList){
            //Message没有的属性通过子查询连接涉及的表
            List<String> itemList = messageItemsLink.queryItems(msg.getId());
            //遍历新的信息，封装进messageInfo
            msg.setItemList(itemList);
        }
        //把留言列表组装到map一并发出
        Map<String,Object> result = new HashMap<>();
        result.put("messages",messageList);

        return result;
    }


    //查询留言业务
    public Map<String,Object> queryAll(Integer index, Integer pageSize) {

        System.out.println("***********************************");
        System.out.println("索引："+index);
        System.out.println("每页大小："+pageSize);

        //查出留言列表
        List<MessageInfo> messageList = messageMapper.queryAll(index,pageSize);
        Integer total = messageMapper.queryTotal();


        Map<String,Object> result =  queryResult(messageList);
        result.put("total",total);

        return result;
    }

    //查出指定用户的留言的业务
    @Override
    public Map<String, Object> queryByUsername(String username, Integer index, Integer pageSize) {

        List<MessageInfo> messageList = messageMapper.queryByUsername(username,index,pageSize);
        Integer total = messageMapper.queryTotal();

        Map<String,Object> result =  queryResult(messageList);
        result.put("total",total);

        return result;
    }

    //删除留言业务,返回删除的id
    @Override
    @Transactional
    public Integer delete(Integer id) {
        messageItemsLink.deleteLink(id);
        int rows = messageMapper.delete(id,CurrentHolder.getCurrentId());
        if(rows == 0){
            throw new RuntimeException("删除失败，找不到当前用户的这条留言");
        }
        return id;
    }

    //查询可用项目
    @Override
    public List<Map<Integer,String>> queryItem() {
        return itemsMapper.queryItem();
    }

}
