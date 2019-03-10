/*
 * 文件名称：ProductQueryRequest.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190310 23:20
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190310-01         Rushing0711     M201903102320 新建文件
 ********************************************************************************/
package com.coding.sell.req;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.coding.sell.res.ProductQueryResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductQueryRequest extends AppRequest<ProductQueryResponse> {
    private static final long serialVersionUID = 2355291019703240429L;
}
