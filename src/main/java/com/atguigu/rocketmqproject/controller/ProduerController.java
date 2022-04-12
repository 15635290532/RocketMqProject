package com.atguigu.rocketmqproject.controller;

import com.alibaba.fastjson.JSON;
import com.atguigu.rocketmqproject.pojo.Order;
import com.atguigu.rocketmqproject.service.MessageService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProduerController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/abc")
    public String test(){

        System.out.println("这是主分支.....");
        System.out.println("从分支....");

        String message = messageService.inertIntoMessage();

        return message;
    }

}
