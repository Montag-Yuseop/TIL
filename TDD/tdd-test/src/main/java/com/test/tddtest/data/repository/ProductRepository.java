package com.test.tddtest.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.test.tddtest.data.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}
