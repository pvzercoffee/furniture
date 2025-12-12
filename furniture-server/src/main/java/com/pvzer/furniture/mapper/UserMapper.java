package com.pvzer.furniture.mapper;

import com.pvzer.furniture.pojo.LoginInfo;
import com.pvzer.furniture.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {

    void signup(User user);

    @Select("select * from users where username = #{username}")
    public User login(User user);

    @Select("select username,email,name,telephone,birthday,gender from users where id = #{id}")
    public LoginInfo me(Integer id);

    void modify(User user);
}
