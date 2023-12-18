package com.hub.toy.data.handler.impl;

import com.hub.toy.data.dao.ProductDao;
import com.hub.toy.data.entity.ProductEntity;
import com.hub.toy.data.handler.ProductDataHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductDataHandlerImpl implements ProductDataHandler {

    private  final ProductDao productDao;

    @Override
    public ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock) {
        ProductEntity productEntity = new ProductEntity(productId, productName, productPrice, productStock);
        return productDao.saveProduct(productEntity);
    }

    @Override
    public ProductEntity getProductEntity(String productId) {
        return productDao.getProduct(productId);
    }
}
