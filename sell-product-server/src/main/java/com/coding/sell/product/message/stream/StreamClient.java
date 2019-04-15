/*
 * 文件名称：StreamClient.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190414 11:26
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190414-01         Rushing0711     M201904141126 新建文件
 ********************************************************************************/
package com.coding.sell.product.message.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface StreamClient {

    String OUTPUT_CHANNEL = "output_channel";

    String INPUT_CHANNEL = "input_channel";

    String OUTPUT_CHANNEL2 = "output_channel2";

    String INPUT_CHANNEL2 = "input_channel2";

    String INPUT_CHANNEL21 = "input_channel21";

    @Output(StreamClient.OUTPUT_CHANNEL)
    MessageChannel outputChannel();

    @Input(StreamClient.INPUT_CHANNEL)
    SubscribableChannel inputChannel();

    @Output(StreamClient.OUTPUT_CHANNEL2)
    MessageChannel outputChannel2();

    @Input(StreamClient.INPUT_CHANNEL2)
    SubscribableChannel inputChannel2();

    @Input(StreamClient.INPUT_CHANNEL21)
    SubscribableChannel inputChannel21();
}
