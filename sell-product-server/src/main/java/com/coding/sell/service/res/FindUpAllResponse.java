/*
 * 文件名称：FindUpAllResponse.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190312 21:00
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190312-01         Rushing0711     M201903122100 新建文件
 ********************************************************************************/
package com.coding.sell.service.res;

import com.coding.helpers.tool.cmp.api.AppResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class FindUpAllResponse extends AppResponse {
    private static final long serialVersionUID = -2328943867961928708L;

    @JsonProperty("data")
    private List<ProductVO> productVOList;

    @Data
    public static class ProductVO implements Serializable {

        private static final long serialVersionUID = -2870583468624341181L;

        /** 商品类型名称. */
        @JsonProperty("name")
        private String categoryName;

        /** 商品类型值. */
        @JsonProperty("type")
        private Integer categoryType;

        private List<FoodVO> foodList;
    }

    @Data
    public static class FoodVO implements Serializable {

        private static final long serialVersionUID = -7780375263109327785L;

        private Long id;

        @JsonProperty("name")
        private String productName;

        private BigDecimal productPrice;

        private String productDescription;

        private String productIcon;
    }
}
