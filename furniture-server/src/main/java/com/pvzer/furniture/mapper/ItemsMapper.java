package com.pvzer.furniture.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ItemsMapper {
    //查询可用留言项目
    @Select("select id,name from items order by id")
    List<Map<Integer,String>> queryItem();
}
