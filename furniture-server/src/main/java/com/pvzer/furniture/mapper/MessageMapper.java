package com.pvzer.furniture.mapper;

import com.pvzer.furniture.pojo.Message;
import com.pvzer.furniture.pojo.MessageItemsLink;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into messages(user_id,name,telephone,email,text) value (#{userId},#{name},#{telephone},#{email},#{text})")
    void addToMessages(Message message);

    void addToMessageItemsLink(Integer messageId,List<Integer> itemList);

}
