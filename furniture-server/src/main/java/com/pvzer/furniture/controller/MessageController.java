package com.pvzer.furniture.controller;

import com.pvzer.furniture.pojo.Message;
import com.pvzer.furniture.pojo.MessageInfo;
import com.pvzer.furniture.pojo.Result;
import com.pvzer.furniture.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageServiceImpl messageService;

    //添加留言
    @PostMapping
    public Result add(@RequestBody Message message){

        messageService.add(message);
        return Result.success("发表成功",null);
    }

    //查询留言
    @GetMapping
    public Result query(@RequestParam Integer page, @RequestParam Integer pageSize){
        Map<String,Object> messageInfoList =  messageService.query(page,pageSize);
        return Result.success(messageInfoList);
    }

    //删除留言
    @DeleteMapping("/{messageId}")
    public Result delete(@PathVariable Integer messageId){
        messageService.delete(messageId);
        return Result.success("已删除");
    }

    //查询留言可选项目
    @GetMapping("/queryItem")
    public Result queryItem(){
        List<Map<Integer,String>> result = messageService.queryItem();
        return Result.success(result);
    }
}
