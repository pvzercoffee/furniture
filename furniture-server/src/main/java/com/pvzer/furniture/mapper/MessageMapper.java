package com.pvzer.furniture.mapper;

import com.pvzer.furniture.pojo.Message;
import com.pvzer.furniture.pojo.MessageInfo;
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
    List<MessageInfo> queryAll();

    //查询指定用户的留言内容
    List<MessageInfo> queryByUsername(String username);

    //查询指定id发布的留言id
    @Select("select m.id from messages m where m.user_id = #{id}")
    List<Integer> queryIdByUserId(Integer id);

    //查询单条留言的item名
    List<String> queryItems(Integer messageId);

    //删除留言内容
    @Delete("delete from messages where id = #{messageId} and user_id = #{userId}")
    Integer delete(Integer messageId,Integer userId);

    //删除留言多表关系
    @Delete("delete from message_items_link where message_id = #{messageId} ")
    Integer deleteLink(Integer messageId);

    //注销指定用户的多表关系
    void destroyMessageItemsLink(List<Integer> messageIdList);

    //清空指定用户的所有留言
    @Delete("delete from messages where user_id = #{userId}")
    void destroyMessageById(Integer userId);

    //查询可用留言项目
    @Select("select id,name from items order by id")
    List<Map<Integer,String>> queryItem();
}
