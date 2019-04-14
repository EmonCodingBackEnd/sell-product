/*
 * 文件名称：StreamController.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190414 11:32
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190414-01         Rushing0711     M201904141132 新建文件
 ********************************************************************************/
package com.coding.sell.product.controller;

import com.coding.sell.product.common.message.stream.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class StreamController {

    @Autowired private StreamClient streamClient;

    @PostMapping("/stream/send")
    public void process() {
        streamClient.outputChannel().send(MessageBuilder.withPayload("now  " + new Date()).build());
    }
}
