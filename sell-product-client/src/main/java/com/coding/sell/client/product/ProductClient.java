/*
 * 文件名称：ProductService.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190310 23:15
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190310-01         Rushing0711     M201903102315 新建文件
 ********************************************************************************/
package com.coding.sell.client.product;

import com.coding.sell.product.service.req.DecreaseStockRequest;
import com.coding.sell.product.service.req.ListForOrderRequest;
import com.coding.sell.product.service.req.OnSaleListRequest;
import com.coding.sell.product.service.res.DecreaseStockResponse;
import com.coding.sell.product.service.res.ListForOrderResponse;
import com.coding.sell.product.service.res.OnSaleListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "SELL-PRODUCT-PROVIDER")
public interface ProductClient {

    /** 查询所有在架商品. */
    @PostMapping("/onSaleList")
    OnSaleListResponse onSaleList(OnSaleListRequest request);

    /** 查询订单所需商品. */
    @PostMapping("/listForOrder")
    ListForOrderResponse listForOrder(ListForOrderRequest request);

    /** 商品库存扣减. */
    @PostMapping("/decreaseStock")
    DecreaseStockResponse decreaseStock(DecreaseStockRequest request);
}
