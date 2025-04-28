package com.hsryuuu.flashsale.orders.dto;

import com.hsryuuu.flashsale.orders.OrderStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private UUID id;

    private UUID memberId;

    private UUID eventId;

    private Integer quantity;

    private BigDecimal orderPrice;

    private OrderStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
