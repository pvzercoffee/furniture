package com.pvzer.furniture.controller;

import com.pvzer.furniture.pojo.Message;
import com.pvzer.furniture.pojo.Result;
import com.pvzer.furniture.service.MessageService;
import com.pvzer.furniture.service.impl.MessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Autowired
    MessageServiceImpl messageService;

    @PostMapping("/message")
    public Result add(@RequestBody Message message){

        String msg = messageService.add(message);
        return Result.success(msg,null);
    }
}
