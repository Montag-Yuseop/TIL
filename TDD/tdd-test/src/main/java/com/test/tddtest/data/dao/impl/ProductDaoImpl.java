package com.test.tddtest.data.dao.impl;

import com.test.tddtest.data.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.tddtest.data.dao.ProductDao;
import com.test.tddtest.data.repository.ProductRepository;


@Service
public class ProductDaoImpl implements ProductDao {
    ProductRepository productRepository;

    @Autowired
    public ProductDaoImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

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
