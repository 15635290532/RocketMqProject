package com.atguigu.rocketmqproject.service.impl;

import com.atguigu.rocketmqproject.mapper.OrderMapper;
import com.atguigu.rocketmqproject.pojo.Order;
import com.atguigu.rocketmqproject.service.OrderService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Autowired
    OrderMapper orderMapper;

    public String insertIntoOrder(Order order){
        int insert = orderMapper.insert(order);
        return String.valueOf(insert);
    }



}
