package com.test.tddtest.controller;

import com.test.tddtest.dto.ProductDto;
import com.test.tddtest.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product/{productId}")
    public ProductDto getProduct(@PathVariable String productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/create")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {

        String productId = productDto.getProductId();
        String productName = productDto.getProductName();
        int productPrice = productDto.getProductPrice();
        int productStock = productDto.getProductStock();

        return productService.saveProduct(productId, productName, productPrice, productStock);
    }

    @DeleteMapping("/delete/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId) {
        return null;
    }

}
