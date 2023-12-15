package com.test.tddtest.controller;

import com.test.tddtest.dto.ProductDto;
import com.test.tddtest.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable String productId) {
        long startTime = System.currentTimeMillis();
        log.info("[ProductController] perform {} of API", "get Product");

        ProductDto productDto = productService.getProduct(productId);
        log.info("[ProductController] Response :: productID = {}, productName = {}, productPrice = {}, productStock = {}, ResponseTime = {}ms", productDto.getProductId(), productDto.getProductName(), productDto.getProductPrice(), productDto.getProductStock(), (System.currentTimeMillis() - startTime));

        return productDto;
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {

        String productId = productDto.getProductId();
        String productName = productDto.getProductName();
        int productPrice = productDto.getProductPrice();
        int productStock = productDto.getProductStock();

//        log.trace("log_trace"); // 안나온다
//        log.debug("log_debug"); // 안나온다
//        log.info("log_info");
//        log.warn("log_warn");
//        log.error("log_error");

        ProductDto response = productService.saveProduct(productId, productName, productPrice, productStock);

        log.info("[createProduct] Response >> productId : {}, productName : {}, productPrice : {}, product Stock: {}", response.getProductId(), response.getProductName(), response.getProductPrice(), response.getProductStock());

        return ResponseEntity.status(HttpStatus.OK).body(productDto);
    }

    @DeleteMapping("/delete/{productId}")
    public ProductDto deleteProduct(@PathVariable String productId) {
        return null;
    }

    @PostMapping("/exception")
    public void exceptionTest() throws Exception {
        throw new Exception();
    }

//    @ExceptionHandler(value = Exception.class)
//    public ResponseEntity<Map<String, String>> ExceptinoHandler(Exception e) {
//        HttpHeaders responseHeaders = new HttpHeaders();
//        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
//
//        log.info(e.getLocalizedMessage());
//        log.info("Controller 내 Exception Handler 호출");
//
//        Map<String, String> map = new HashMap<>();
//        map.put("error type", httpStatus.getReasonPhrase());
//        map.put("code", "400");
//        map.put("message", "에러 발생");
//
//        return new ResponseEntity<>(map, responseHeaders, httpStatus);
//
//    }

}
