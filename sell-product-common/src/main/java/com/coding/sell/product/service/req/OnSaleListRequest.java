/*
 * 文件名称：FindUpAllRequest.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190312 21:01
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190312-01         Rushing0711     M201903122101 新建文件
 ********************************************************************************/
package com.coding.sell.product.service.req;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.coding.sell.product.service.res.OnSaleListResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OnSaleListRequest extends AppRequest<OnSaleListResponse> {
    private static final long serialVersionUID = -8069976142051120371L;
}
