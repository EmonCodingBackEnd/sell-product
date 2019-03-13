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
