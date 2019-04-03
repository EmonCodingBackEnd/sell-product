/*
 * 文件名称：ProductController.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190310 08:27
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190310-01         Rushing0711     M201903100827 新建文件
 ********************************************************************************/
package com.coding.sell.product.controller;

import com.coding.sell.product.service.api.ProductService;
import com.coding.sell.product.service.req.DecreaseStockRequest;
import com.coding.sell.product.service.req.ListForOrderRequest;
import com.coding.sell.product.service.req.OnSaleListRequest;
import com.coding.sell.product.service.res.DecreaseStockResponse;
import com.coding.sell.product.service.res.ListForOrderResponse;
import com.coding.sell.product.service.res.OnSaleListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired ProductService productService;

    @PostMapping("/product/onSaleList")
    public OnSaleListResponse onSaleList(@RequestBody OnSaleListRequest request) {
        return productService.onSaleList(request);
    }

    @PostMapping("/product/listForOrder")
    public ListForOrderResponse listForOrder(@RequestBody ListForOrderRequest request) {
        return productService.listForOrder(request);
    }

    @PostMapping("/product/decreaseStock")
    public DecreaseStockResponse decreaseStock(@RequestBody DecreaseStockRequest request) {
        return productService.decreaseStock(request);
    }
}
