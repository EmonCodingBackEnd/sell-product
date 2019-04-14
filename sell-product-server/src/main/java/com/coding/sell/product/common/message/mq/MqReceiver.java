/*
 * 文件名称：MqReceiver.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190406 07:48
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190406-01         Rushing0711     M201904060748 新建文件
 ********************************************************************************/
package com.coding.sell.product.common.message.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收mq消息.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190406 07:51</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
@Slf4j
public class MqReceiver {

    // 第一种方式：需要预先创建队列名为 myQueue 的队列
    /*@RabbitListener(queues = "myQueue")
    public void process(String message) {
        log.info("MqReceiver: {}", message);
    }*/

    // 第二种方式：自动创建队列名为 myQueue 的队列
    /*@RabbitListener(queuesToDeclare = @Queue("myQueue"))
    public void process(String message) {
        log.info("MqReceiver: {}", message);
    }*/

    // 第三种方式：自动创建队列名为 myQueue 的队列，并与Exchange绑定
    @RabbitListener(
        bindings = @QueueBinding(value = @Queue("myQueue"), exchange = @Exchange("myExchange"))
    )
    public void process(String message) {
        log.info("MqReceiver: {}", message);
    }

    /** 消息接收方：数码供应商服务，接收消息. */
    @RabbitListener(
        bindings =
                @QueueBinding(
                    exchange = @Exchange("myOrderExchange"),
                    key = "computer",
                    value = @Queue("myComputerQueue")
                )
    )
    public void processComputer(String message) {
        log.info("MqReceiver: {}, key: computer", message);
    }

    /** 消息接收方：水果供应商服务，接收消息. */
    @RabbitListener(
        bindings =
                @QueueBinding(
                    exchange = @Exchange("myOrderExchange"),
                    key = "fruit",
                    value = @Queue("myFruitQueue")
                )
    )
    public void processFruit(String message) {
        log.info("MqReceiver: {}, key: fruit", message);
    }
}
