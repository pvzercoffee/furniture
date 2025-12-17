package com.pvzer.furniture.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageItemsLink {
    //删除留言多表关系
    @Delete("delete from message_items_link where message_id = #{messageId} ")
    Integer deleteLink(Integer messageId);

    //注销指定用户的多表关系
    void destroyMessageItemsLink(List<Integer> messageIdList);

    //维护一留言对多item的表
    void addToMessageItemsLink(Integer messageId,List<Integer> itemList);

    //查询单条留言的item名
    List<String> queryItems(Integer messageId);

}
