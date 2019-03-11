/*
 * 文件名称：ProductInfoRepositoryTest.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved. 
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190310 08:52
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190310-01         Rushing0711     M201903100852 新建文件
 ********************************************************************************/
package com.coding.sell.repository;

import com.coding.sell.SellProductApplicationTests;
import com.coding.sell.common.DictDefinition;
import com.coding.sell.domain.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductInfoRepositoryTest extends SellProductApplicationTests {

    @Autowired private ProductInfoRepository productRepository;

    @Test
    public void findByProductStatusAndDeleted() throws Exception {
        List<ProductInfo> productList =
                productRepository.findByProductStatusAndDeleted(1, DictDefinition.Deleted.NO);
        Assert.assertTrue(productList.size() > 0);
    }
}
