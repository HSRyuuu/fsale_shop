package com.hsryuuu.flashsale.event.flashsale.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private UUID productId;
    @Min(1)
    private BigDecimal salePrice;
    @Min(1)
    private Integer totalQuantity;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

}
