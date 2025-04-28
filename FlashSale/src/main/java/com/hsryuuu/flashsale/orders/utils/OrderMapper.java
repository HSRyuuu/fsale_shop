package com.hsryuuu.flashsale.orders.utils;

import com.hsryuuu.flashsale.orders.Orders;
import com.hsryuuu.flashsale.orders.dto.OrderDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class OrderMapper {

    public static OrderDto fromEntity(Orders order) {
        return OrderDto.builder()
                .id(order.getId())
                .memberId(order.getMember().getId())
                .eventId(order.getEvent().getId())
                .quantity(order.getQuantity())
                .orderPrice(order.getOrderPrice())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }
}
