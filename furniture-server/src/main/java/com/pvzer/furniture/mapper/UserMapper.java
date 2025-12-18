package com.pvzer.furniture.mapper;

import com.pvzer.furniture.pojo.LoginInfo;
import com.pvzer.furniture.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    //注册
    void signup(User user);

    //登录
    @Select("select * from users where username = #{username}")
    User login(String username);

    //更新最后登录时间
    @Update("update users set last_login = now() where id = #{id}")
    void updateLoginTime(Integer id);

    //根据句id查询用户信息
    @Select("select username,email,name,telephone,birthday,gender from users where id = #{id}")
    LoginInfo me(Integer id);

    //修改用户信息
    void modify(User user);

    //根据id注销用户
    @Delete("delete from users where id = #{userId}")
    void destroyById(Integer userId);
}
