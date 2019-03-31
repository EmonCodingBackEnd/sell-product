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
import com.coding.sell.domain.ProductCategory;
import com.coding.sell.domain.ProductInfo;
import com.coding.sell.exception.AppStatus;
import com.coding.sell.repository.ProductCategoryRepository;
import com.coding.sell.repository.ProductInfoRepository;
import com.coding.sell.service.api.ProductService;
import com.coding.sell.service.req.DecreaseStockRequest;
import com.coding.sell.service.req.ListForOrderRequest;
import com.coding.sell.service.req.OnSaleListRequest;
import com.coding.sell.service.res.DecreaseStockResponse;
import com.coding.sell.service.res.ListForOrderResponse;
import com.coding.sell.service.res.OnSaleListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired private ProductInfoRepository productInfoRepository;

    @Autowired private ProductCategoryRepository categoryRepository;

    @Override
    public OnSaleListResponse onSaleList(OnSaleListRequest request) {
        OnSaleListResponse response = new OnSaleListResponse();
        List<ProductInfo> infoList =
                productInfoRepository.findByProductStatusAndDeleted(
                        DictDefinition.ProductStatus.UP.getValue(), DictDefinition.Deleted.NO);

        List<Integer> categoryTypeList =
                infoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());
        List<ProductCategory> categoryList =
                categoryRepository.findByCategoryTypeInAndDeleted(
                        categoryTypeList, DictDefinition.Deleted.NO);

        List<OnSaleListResponse.CategoryVO> categoryVOList = new ArrayList<>();
        for (ProductCategory category : categoryList) {
            OnSaleListResponse.CategoryVO categoryVO = new OnSaleListResponse.CategoryVO();
            categoryVO.setCategoryName(category.getCategoryName());
            categoryVO.setCategoryType(category.getCategoryType());

            List<OnSaleListResponse.ItemVO> itemVOList = new ArrayList<>();
            for (ProductInfo info : infoList) {
                OnSaleListResponse.ItemVO foodVO = new OnSaleListResponse.ItemVO();
                BeanUtils.copyProperties(info, foodVO);
                itemVOList.add(foodVO);
            }
            categoryVO.setItemVOList(itemVOList);

            categoryVOList.add(categoryVO);
        }
        if (CollectionUtils.isEmpty(categoryVOList)) {
            log.error("【商品查询】查询商品信息失败！");
            throw new AppException(AppStatus.PARAM_ERROR);
        }
        response.setData(categoryVOList);
        return response;
    }

    @Override
    public ListForOrderResponse listForOrder(ListForOrderRequest request) {
        ListForOrderResponse response = new ListForOrderResponse();

        List<ProductInfo> productInfoList =
                productInfoRepository.findByIdIn(request.getProductIdList());
        List<ListForOrderResponse.ItemVO> itemVOList =
                productInfoList
                        .stream()
                        .map(
                                e -> {
                                    ListForOrderResponse.ItemVO itemVO =
                                            new ListForOrderResponse.ItemVO();
                                    BeanUtils.copyProperties(e, itemVO);
                                    return itemVO;
                                })
                        .collect(Collectors.toList());
        response.setData(itemVOList);

        return response;
    }

    @Transactional
    @Override
    public DecreaseStockResponse decreaseStock(DecreaseStockRequest request) {
        DecreaseStockResponse response = new DecreaseStockResponse();
        for (DecreaseStockRequest.CartDTO cartDTO : request.getCartDTOList()) {
            Optional<ProductInfo> productInfoOptional =
                    productInfoRepository.findById(cartDTO.getProductId());
            if (!productInfoOptional.isPresent()) {
                log.error("【库存扣减】商品不存在, productId={}", cartDTO.getProductId());
                throw new AppException(AppStatus.OBJECT_NOT_EXIST, "商品不存在");
            }

            ProductInfo productInfo = productInfoOptional.get();
            Integer remainStock = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (remainStock < 0) {
                log.error("【库存扣减】库存不足, productId={}", cartDTO.getProductId());
                throw new AppException(AppStatus.BUSINESS_CHECK_ERROR, "库存不足");
            }

            productInfo.setProductStock(remainStock);
            productInfoRepository.save(productInfo);
        }
        return response;
    }
}
