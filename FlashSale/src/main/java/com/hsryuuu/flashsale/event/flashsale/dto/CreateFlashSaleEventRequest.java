package com.hsryuuu.flashsale.event.flashsale.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateFlashSaleEventRequest {
    private UUID productId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal salePrice;
    private Integer totalQuantity;
}
