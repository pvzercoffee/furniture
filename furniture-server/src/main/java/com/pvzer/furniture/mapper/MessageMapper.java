package com.pvzer.furniture.mapper;

import com.pvzer.furniture.pojo.Message;
import com.pvzer.furniture.pojo.MessageInfo;
import com.pvzer.furniture.pojo.MessageItem;
import com.pvzer.furniture.pojo.MessageQueryInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface MessageMapper {

    //插入留言内容
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into messages(user_id,name,telephone,email,text) value (#{userId},#{name},#{telephone},#{email},#{text})")
    void addToMessages(Message message);

    //维护一留言对多item的表
    void addToMessageItemsLink(Integer messageId,List<Integer> itemList);

    //查询留言内容
    @Select("select id,user_id,name,telephone,email,text from messages")
    List<Message> query();

    //查询留言条目数
    @Select("select count(id) from messages")
    Integer queryNum();

    //查询单条留言的item名
    List<MessageQueryInfo> queryItems(Integer messageId);

    //删除留言内容
    @Delete("delete from messages where id = #{messageId}")
    void delete(Integer messageId);

    //删除留言多表关系
    @Delete("delete from message_items_link where message_id = #{messageId}")
    void deleteLink(Integer messageId);

    //查询可用留言项目
    @Select("select id,name from items order by id")
    List<Map<Integer,String>> queryItem();
}
