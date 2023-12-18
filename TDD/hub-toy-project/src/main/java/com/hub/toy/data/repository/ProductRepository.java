package com.hub.toy.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hub.toy.data.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}
