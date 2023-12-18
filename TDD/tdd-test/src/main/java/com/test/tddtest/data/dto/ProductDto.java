package com.test.tddtest.data.dto;

import com.test.tddtest.data.entity.ProductEntity;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    @NotBlank
    private String productId;

    @NotNull
    private String productName;

    @NotNull
    @Min(500)
    @Max(1000)
    private int productPrice;

    @NotNull
    private int productStock;

    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .productId(productId)
                .productName(productName)
                .productPrice(productPrice)
                .productStock(productStock)
                .build();
    }

}
