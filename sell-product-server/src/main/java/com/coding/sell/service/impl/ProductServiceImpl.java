/*
 * 文件名称：ProductServiceImpl.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190313 22:19
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190313-01         Rushing0711     M201903132219 新建文件
 ********************************************************************************/
package com.coding.sell.service.impl;

import com.coding.sell.common.DictDefinition;
import com.coding.sell.domain.ProductInfo;
import com.coding.sell.repository.ProductInfoRepository;
import com.coding.sell.service.api.ProductService;
import com.coding.sell.service.req.FindUpAllRequest;
import com.coding.sell.service.res.FindUpAllResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired private ProductInfoRepository infoRepository;

    @Override
    public FindUpAllResponse findUpAll(FindUpAllRequest request) {
        FindUpAllResponse response = new FindUpAllResponse();
        List<ProductInfo> infoList =
                infoRepository.findByProductStatusAndDeleted(
                        DictDefinition.ProductStatus.UP.getValue(), DictDefinition.Deleted.NO);
        response.setProductInfoList(infoList);
        return response;
    }
}
