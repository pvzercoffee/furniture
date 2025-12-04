package com.pvzer.furniture.mapper;

import com.pvzer.furniture.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    void signup(User user);

    @Select("select * from users where username = #{username}")
    public User login(User user);
}
