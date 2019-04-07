/*
 * 文件名称：MqController.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190406 07:52
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190406-01         Rushing0711     M201904060752 新建文件
 ********************************************************************************/
package com.coding.sell.product.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MqController {

    @Autowired AmqpTemplate amqpTemplate;

    @PostMapping("/mq/send")
    public void send() {
        amqpTemplate.convertAndSend("myQueue", "now " + new Date());
    }

    @PostMapping("/mq/sendComputer")
    public void sendComputer() {
        amqpTemplate.convertAndSend("myOrderExchange", "computer", "now " + new Date());
    }

    @PostMapping("/mq/sendFruit")
    public void sendFruit() {
        amqpTemplate.convertAndSend("myOrderExchange", "fruit", "now " + new Date());
    }
}
