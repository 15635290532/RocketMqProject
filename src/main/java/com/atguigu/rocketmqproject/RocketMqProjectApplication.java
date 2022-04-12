package com.atguigu.rocketmqproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.atguigu.rocketmqproject.mapper")
public class RocketMqProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqProjectApplication.class, args);
    }

}
