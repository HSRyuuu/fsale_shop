package com.hsryuuu.flashsale.event.flashsale.dto;

import com.hsryuuu.flashsale.product.dto.ProductDto;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlashSaleEventDto {
    private UUID id;
    private UUID productId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal salePrice;
    private Integer totalQuantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ProductDto product;
}
