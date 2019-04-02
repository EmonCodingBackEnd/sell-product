/*
 * 文件名称：DecreaseStockRequest.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190331 22:31
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190331-01         Rushing0711     M201903312231 新建文件
 ********************************************************************************/
package com.coding.sell.product.service.req;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.coding.sell.product.service.res.DecreaseStockResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DecreaseStockRequest extends AppRequest<DecreaseStockResponse> {
    private static final long serialVersionUID = 2239990075257657018L;

    @NotEmpty(message = "请先添加商品到购物车")
    @JsonProperty("items")
    private List<CartDTO> cartDTOList;

    @Data
    public static class CartDTO implements Serializable {

        private static final long serialVersionUID = 6337543773481612333L;

        /** 商品ID. */
        private Long productId;

        /** 购买数量. */
        private Integer productQuantity;
    }
}
