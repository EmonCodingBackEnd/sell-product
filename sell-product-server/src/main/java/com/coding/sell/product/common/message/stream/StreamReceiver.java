/*
 * 文件名称：StreamReceiver.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190414 11:30
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190414-01         Rushing0711     M201904141130 新建文件
 ********************************************************************************/
package com.coding.sell.product.common.message.stream;

import com.coding.sell.product.domain.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@EnableBinding(StreamClient.class)
@Component
@Slf4j
public class StreamReceiver {

    @StreamListener(StreamClient.INPUT_CHANNEL)
    public void process(Object message) {
        log.info("StreamReceiver: {}", message);
    }

    @StreamListener(StreamClient.INPUT_CHANNEL2)
    @SendTo(StreamClient.INPUT_CHANNEL21)
    public String process2(ProductInfo message) {
        log.info("StreamReceiver2: {}", message);
        return "StreamReceiver has complete";
    }

    @StreamListener(StreamClient.INPUT_CHANNEL21)
    public void process21(String message) {
        log.info("StreamReceiver21: {}", message);
    }
}
