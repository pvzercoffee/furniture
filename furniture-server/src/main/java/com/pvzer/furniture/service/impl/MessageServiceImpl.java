package com.pvzer.furniture.service.impl;

import com.github.pagehelper.PageHelper;
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

    //查询留言业务
    public Map<String,Object> query(Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);

        //查出留言列表
        List<MessageInfo> messageList = messageMapper.query();

        List<MessageInfo> messageInfoList = new ArrayList<>();

        //获取每条留言的item
        for(MessageInfo msg : messageList){

            //Message没有的属性通过子查询连接涉及的表
            List<String> itemList = messageMapper.queryItems(msg.getId());
            //遍历新的信息，封装进messageInfo

            msg.setItemList(itemList);
            messageInfoList.add(msg);
        }

        //查询总记录数
        Integer total = messageMapper.queryNum();

        //把总记录数和留言列表组装到map一并发出
        Map<String,Object> result = new HashMap<>();
        result.put("total", total);
        result.put("messages",messageInfoList);

        return result;
    }

    //删除留言
    @Override
    @Transactional
    public void delete(Integer id) {

        messageMapper.deleteLink(id);
        int rows = messageMapper.delete(id,CurrentHolder.getCurrentId());
        if(rows == 0){
            throw new RuntimeException("删除失败，找不到当前用户的这条留言");
        }
    }

    //查询可用项目
    @Override
    public List<Map<Integer,String>> queryItem() {
        return messageMapper.queryItem();
    }
}
