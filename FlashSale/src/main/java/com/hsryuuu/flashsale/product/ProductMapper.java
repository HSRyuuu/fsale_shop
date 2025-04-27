package com.hsryuuu.flashsale.product;

import com.hsryuuu.flashsale.product.dto.ProductDto;

public final class ProductMapper {
    private ProductMapper() {
    }

    public static ProductDto fromEntity(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .sku(product.getSku())
                .status(product.getStatus())
                .stockQuantity(product.getStockQuantity())
                .categoryId(product.getCategory().getId())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
