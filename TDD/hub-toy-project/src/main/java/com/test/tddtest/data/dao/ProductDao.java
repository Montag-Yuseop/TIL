package com.test.tddtest.data.dao;

import com.test.tddtest.data.entity.ProductEntity;

public interface ProductDao {

    ProductEntity saveProduct(ProductEntity productEntity);

    ProductEntity getProduct(String productId);
}
