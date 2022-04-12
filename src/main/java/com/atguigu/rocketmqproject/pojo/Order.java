package com.atguigu.rocketmqproject.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@TableName("`order`")
public class Order {

    @TableId("oid")
    private int oid;
    @TableField("`name`")
    private String name;

}
