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

import com.coding.helpers.tool.cmp.exception.AppException;
import com.coding.sell.common.DictDefinition;
import com.coding.sell.exception.AppStatus;
import com.coding.sell.domain.ProductCategory;
import com.coding.sell.domain.ProductInfo;
import com.coding.sell.repository.ProductCategoryRepository;
import com.coding.sell.repository.ProductInfoRepository;
import com.coding.sell.service.api.ProductService;
import com.coding.sell.service.req.FindUpAllRequest;
import com.coding.sell.service.res.FindUpAllResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired private ProductInfoRepository infoRepository;

    @Autowired private ProductCategoryRepository categoryRepository;

    @Override
    public FindUpAllResponse findUpAll(FindUpAllRequest request) {
        FindUpAllResponse response = new FindUpAllResponse();
        List<ProductInfo> infoList =
                infoRepository.findByProductStatusAndDeleted(
                        DictDefinition.ProductStatus.UP.getValue(), DictDefinition.Deleted.NO);

        List<Integer> categoryTypeList =
                infoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> categoryList =
                categoryRepository.findByCategoryTypeInAndDeleted(
                        categoryTypeList, DictDefinition.Deleted.NO);

        List<FindUpAllResponse.ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory category : categoryList) {
            FindUpAllResponse.ProductVO productVO = new FindUpAllResponse.ProductVO();
            productVO.setCategoryName(category.getCategoryName());
            productVO.setCategoryType(category.getCategoryType());

            List<FindUpAllResponse.FoodVO> foodVOList = new ArrayList<>();
            for (ProductInfo info : infoList) {
                FindUpAllResponse.FoodVO foodVO = new FindUpAllResponse.FoodVO();
                BeanUtils.copyProperties(info, foodVO);
                foodVOList.add(foodVO);
            }
            productVO.setFoodList(foodVOList);

            productVOList.add(productVO);
        }
        if (CollectionUtils.isEmpty(productVOList)) {
            log.error("【商品查询】查询商品信息失败！");
            throw new AppException(AppStatus.PARAM_ERROR);
        }
        response.setData(productVOList);
        return response;
    }
}
