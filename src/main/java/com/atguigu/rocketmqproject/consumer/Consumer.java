package com.atguigu.rocketmqproject.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atguigu.rocketmqproject.mapper.MessageMapper;
import com.atguigu.rocketmqproject.mapper.OrderMapper;
import com.atguigu.rocketmqproject.pojo.Message;
import com.atguigu.rocketmqproject.pojo.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RocketMQMessageListener(topic = "ddsdsd",consumerGroup = "springboot-mq-consumer-1")
public class Consumer implements RocketMQListener<String> {

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Override
    public void onMessage(String msg) {

        JSONObject jsonObject = JSON.parseObject(msg);
        Message message = JSON.parseObject(jsonObject.getString("message"), Message.class);
        Order order = JSON.parseObject(jsonObject.getString("order"), Order.class);

        try {

            messageMapper.insert(message);

            try {
                orderMapper.insert(order);
                Message message1 = new Message();
                message1.setId(message.getId());
                message1.setStatus("已消费");
                int updateById = messageMapper.updateById(message1);
                System.out.println("是否改变消息状态成功："+ (updateById == 1));

            } catch (Exception e) {

                //删除消息记录表
                messageMapper.deleteById(message.getId());
                //触发延迟消费
                delayMesg(msg);
            }

        } catch (Exception e) {

            Message message1 = messageMapper.selectById(message.getId());
            String status = message1.getStatus();

            if("已消费".equals(status)){
                System.out.println("重复消费，消费失败");
            }else if("消费中".equals(status)){
                //触发延迟消费
                delayMesg(msg);
            }else{
                System.out.println("系统出错");
            }

        }


    }


    public void delayMesg(String msg){

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("触发延迟消费....");
        rocketMQTemplate.convertAndSend("ddsdsd",msg);

    }

}