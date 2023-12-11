package com.test.tddtest.data.handler;

import com.test.tddtest.data.entity.ProductEntity;

public interface ProductDataHandler {
    ProductEntity saveProductEntity(String productId, String productName, int productPrice, int productStock);

    ProductEntity getProductEntity(String productId);

}
