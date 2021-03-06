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
package com.coding.sell.product.service.impl;

import com.coding.helpers.tool.cmp.exception.AppException;
import com.coding.sell.product.common.DictDefinition;
import com.coding.sell.product.common.JsonConverter;
import com.coding.sell.product.domain.ProductCategory;
import com.coding.sell.product.domain.ProductInfo;
import com.coding.sell.product.exception.AppStatus;
import com.coding.sell.product.message.DecreaseStockMessage;
import com.coding.sell.product.repository.ProductCategoryRepository;
import com.coding.sell.product.repository.ProductInfoRepository;
import com.coding.sell.product.service.api.ProductService;
import com.coding.sell.product.service.req.DecreaseStockRequest;
import com.coding.sell.product.service.req.ListForOrderRequest;
import com.coding.sell.product.service.req.OnSaleListRequest;
import com.coding.sell.product.service.res.DecreaseStockResponse;
import com.coding.sell.product.service.res.ListForOrderResponse;
import com.coding.sell.product.service.res.OnSaleListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired AmqpTemplate amqpTemplate;

    @Autowired TransactionTemplate transactionTemplate;

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

    @Override
    public DecreaseStockResponse decreaseStock(DecreaseStockRequest request) {
        DecreaseStockResponse response = new DecreaseStockResponse();
        List<ProductInfo> productInfoList =
                transactionTemplate.execute(
                        new TransactionCallback<List<ProductInfo>>() {
                            @Nullable
                            @Override
                            public List<ProductInfo> doInTransaction(
                                    TransactionStatus transactionStatus) {
                                List<ProductInfo> infoList = null;
                                try {
                                    infoList = decreaseStockOnly(request);
                                } catch (Exception e) {
                                    log.error("扣库存失败", e);
                                    transactionStatus.setRollbackOnly();
                                }
                                return infoList;
                            }
                        });

        if (productInfoList == null) {
            throw new AppException(AppStatus.INNER_SERVER_ERROR, "扣库存失败");
        } else {
            List<DecreaseStockMessage> decreaseStockMessageList =
                    productInfoList
                            .stream()
                            .map(
                                    e -> {
                                        DecreaseStockMessage decreaseStockMessage =
                                                new DecreaseStockMessage();
                                        BeanUtils.copyProperties(e, decreaseStockMessage);
                                        return decreaseStockMessage;
                                    })
                            .collect(Collectors.toList());
            // 发送mq消息
            amqpTemplate.convertAndSend(
                    "productInfo", JsonConverter.toJson(decreaseStockMessageList));
        }
        return response;
    }

    private List<ProductInfo> decreaseStockOnly(DecreaseStockRequest request) {
        List<ProductInfo> productInfoList = new ArrayList<>();
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

            productInfoList.add(productInfo);
        }
        return productInfoList;
    }
}
