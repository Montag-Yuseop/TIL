package com.test.tddtest.service.impl;

import com.test.tddtest.data.entity.ProductEntity;
import com.test.tddtest.data.handler.ProductDataHandler;
import com.test.tddtest.dto.ProductDto;
import com.test.tddtest.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDataHandler productDataHandler;

    @Transactional
    @Override
    public ProductDto saveProduct(String productId, String productName, int productPrice, int productStock) {
        ProductEntity productEntity = productDataHandler.saveProductEntity(productId, productName, productPrice, productStock);

        ProductDto productDto = new ProductDto(productEntity.getProductId(), productEntity.getProductName(), productEntity.getProductPrice(), productEntity.getProductStock());

        return productDto;
    }

    @Transactional
    @Override
    public ProductDto getProduct(String productId) {
        ProductEntity productEntity = productDataHandler.getProductEntity(productId);

        ProductDto productDto = new ProductDto(productEntity.getProductId(), productEntity.getProductName(), productEntity.getProductPrice(), productEntity.getProductStock());

        return productDto;
    }
}
