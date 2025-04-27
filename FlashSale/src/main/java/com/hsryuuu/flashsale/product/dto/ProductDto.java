package com.hsryuuu.flashsale.product.dto;

import com.hsryuuu.flashsale.product.ProductStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private UUID id;
    private String name;
    private BigDecimal price;
    private String sku;
    private ProductStatus status;
    private Integer stockQuantity; // 재고 수량
    private UUID categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
