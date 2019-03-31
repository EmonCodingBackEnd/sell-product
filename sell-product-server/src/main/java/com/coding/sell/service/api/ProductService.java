/*
 * 文件名称：ProductService.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190312 20:43
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190312-01         Rushing0711     M201903122043 新建文件
 ********************************************************************************/
package com.coding.sell.service.api;

import com.coding.sell.service.req.DecreaseStockRequest;
import com.coding.sell.service.req.OnSaleListRequest;
import com.coding.sell.service.req.ListForOrderRequest;
import com.coding.sell.service.res.DecreaseStockResponse;
import com.coding.sell.service.res.OnSaleListResponse;
import com.coding.sell.service.res.ListForOrderResponse;

public interface ProductService {

    /** 查询所有在架商品. */
    OnSaleListResponse onSaleList(OnSaleListRequest request);

    /** 查询订单所需商品. */
    ListForOrderResponse listForOrder(ListForOrderRequest request);

    /** 商品库存扣减. */
    DecreaseStockResponse decreaseStock(DecreaseStockRequest request);
}
