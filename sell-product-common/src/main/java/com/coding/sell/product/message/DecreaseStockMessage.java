/*
 * 文件名称：ProductInfoOutput.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190415 23:15
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190415-01         Rushing0711     M201904152315 新建文件
 ********************************************************************************/
package com.coding.sell.product.message;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DecreaseStockMessage {

    private Long id;

    /** 类目编号. */
    private Integer categoryType;

    /** 商品名称. */
    private String productName;

    /** 单价. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 商品状态. */
    private Integer productStatus;
}
