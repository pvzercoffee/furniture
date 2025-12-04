package com.pvzer.furniture.mapper;

import com.pvzer.furniture.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void signup(User user);
}
