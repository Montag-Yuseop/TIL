package com.test.tddtest.service;

import com.test.tddtest.dto.ProductDto;

public interface ProductService {
    ProductDto saveProduct(String productId, String productName, int productPrice, int productStock);

    ProductDto getProduct(String productId);

}
