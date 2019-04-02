/*
 * 文件名称：ListForOrderRequest.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190331 19:37
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190331-01         Rushing0711     M201903311937 新建文件
 ********************************************************************************/
package com.coding.sell.product.service.req;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.coding.sell.product.service.res.ListForOrderResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListForOrderRequest extends AppRequest<ListForOrderResponse> {

    private static final long serialVersionUID = -1380745170543974608L;

    @NotEmpty(message = "商品ID列表为空")
    @JsonProperty("ids")
    private List<Long> productIdList;
}
