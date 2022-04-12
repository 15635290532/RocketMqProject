package com.atguigu.rocketmqproject.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.atguigu.rocketmqproject.consumer.Consumer;
import com.atguigu.rocketmqproject.mapper.MessageMapper;
import com.atguigu.rocketmqproject.pojo.Message;
import com.atguigu.rocketmqproject.pojo.Order;
import com.atguigu.rocketmqproject.service.MessageService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    //执行发送消息逻辑
    @Override
    public String inertIntoMessage() {

        Random random = new Random();
        int i = random.nextInt(50)+1;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message",new Message(i,"消费中",String.valueOf(System.currentTimeMillis())));
        jsonObject.put("order",new Order(i,"电脑"));

        rocketMQTemplate.convertAndSend("ddsdsd",jsonObject.toString());

        return "ok";
    }
}
