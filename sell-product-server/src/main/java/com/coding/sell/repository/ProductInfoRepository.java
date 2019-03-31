/*
 * 文件名称：ProductInfoRepository.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190310 08:51
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190310-01         Rushing0711     M201903100851 新建文件
 ********************************************************************************/
package com.coding.sell.repository;

import com.coding.sell.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {

    List<ProductInfo> findByProductStatusAndDeleted(Integer productStatus, Integer deleted);

    List<ProductInfo> findByIdIn(List<Long> productIdList);
}
