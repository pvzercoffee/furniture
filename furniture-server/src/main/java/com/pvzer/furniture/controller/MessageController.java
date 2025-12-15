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
@CrossOrigin(origins = "*")  // 允许所有跨域请求
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
    public Result queryAll(@RequestParam Integer page, @RequestParam Integer pageSize){
        Map<String,Object> messageInfoList =  messageService.queryAll(page,pageSize);
        return Result.success(messageInfoList);
    }
    //查询指定用户留言
    @GetMapping("/{username}")
    public Result query(@PathVariable String username, @RequestParam Integer page, @RequestParam Integer pageSize){
        Map<String,Object> messageInfoList =  messageService.queryByUsername(username,page,pageSize);
        return Result.success(messageInfoList);
    }

    //删除留言
    @DeleteMapping("/{messageId}")
    public Result delete(@PathVariable Integer messageId){
        Integer id = messageService.delete(messageId);
        System.out.println("我的id是："+id);
        return Result.success("已删除",id);
    }

    //查询留言可选项目
    @GetMapping("/queryItem")
    public Result queryItem(){
        List<Map<Integer,String>> result = messageService.queryItem();
        return Result.success(result);
    }
}
