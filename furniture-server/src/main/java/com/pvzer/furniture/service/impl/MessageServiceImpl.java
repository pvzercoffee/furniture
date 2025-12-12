package com.pvzer.furniture.service.impl;

import com.github.pagehelper.Page;
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

    //查询留言业务
    public Map<String,Object> query(Integer page, Integer pageSize) {

        PageHelper.startPage(page,pageSize);

        //查出留言列表
        List<Message> messageList = messageMapper.query();

        List<MessageInfo> messageInfoList = new ArrayList<>();

        //通过每个留言的id去遍历n个留言的m个项目,获取留言的item
        for(Message msg : messageList){


            //Message本身具备的属性直接填充
            MessageInfo messageInfo = new MessageInfo();

            messageInfo.setId(msg.getId());
            messageInfo.setText(msg.getText());
            messageInfo.setTelephone(msg.getTelephone());
            messageInfo.setName(msg.getName());
            messageInfo.setEmail(msg.getEmail());

            //Message没有的属性通过子查询连接涉及的表
            List<MessageQueryInfo> queryInfoList = messageMapper.queryItems(msg.getId());
            //遍历新的信息，封装进messageInfo
            String username = queryInfoList.get(0).getUsername();
            List<String> itemList = new ArrayList<>();

            for(MessageQueryInfo queryInfo : queryInfoList){
                if(queryInfo.getItem() != null) itemList.add(queryInfo.getItem());
            }

            messageInfo.setUsername(username);
            messageInfo.setItemList(itemList);

            messageInfoList.add(messageInfo);
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
        messageMapper.delete(id);
    }

    //查询可用项目
    @Override
    public List<Map<Integer,String>> queryItem() {
        return messageMapper.queryItem();
    }
}
