/*
 * 文件名称：ProductCategoryRepositoryTest.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190310 08:55
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190310-01         Rushing0711     M201903100855 新建文件
 ********************************************************************************/
package com.coding.sell.repository;

import com.coding.sell.SellProductApplicationTests;
import com.coding.sell.common.Dicts;
import com.coding.sell.domain.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class ProductCategoryRepositoryTest extends SellProductApplicationTests {

    @Autowired private ProductCategoryRepository categoryRepository;

    @Test
    public void findByCategoryTypeInAndDeleted() throws Exception {
        List<ProductCategory> categoryList =
                categoryRepository.findByCategoryTypeInAndDeleted(
                        Arrays.asList(11, 12), Dicts.Deleted.NO);
        Assert.assertTrue(categoryList.size() == 0);
    }
}
