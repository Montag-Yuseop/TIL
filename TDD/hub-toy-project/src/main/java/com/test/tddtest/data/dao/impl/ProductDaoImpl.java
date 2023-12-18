package com.test.tddtest.data.dao.impl;

import com.test.tddtest.data.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.test.tddtest.data.dao.ProductDao;
import com.test.tddtest.data.repository.ProductRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {
    private final ProductRepository productRepository;

    @Override
    public ProductEntity saveProduct(ProductEntity productEntity) {
        productRepository.save(productEntity);
        return productEntity;
    }

    @Override
    public ProductEntity getProduct(String productId) {
        ProductEntity productEntity = productRepository.getReferenceById(productId);
        return productEntity;
    }
}
