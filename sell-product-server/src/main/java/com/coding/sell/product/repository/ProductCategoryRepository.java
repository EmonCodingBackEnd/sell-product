/*
 * 文件名称：ProductCategoryRepository.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190310 08:53
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190310-01         Rushing0711     M201903100853 新建文件
 ********************************************************************************/
package com.coding.sell.product.repository;

import com.coding.sell.product.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    List<ProductCategory> findByCategoryTypeInAndDeleted(
            List<Integer> categoryTypeList, Integer deleted);
}
