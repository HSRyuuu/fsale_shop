package com.hsryuuu.flashsale.orders.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceOrderRequest {

    @NotNull
    private UUID eventId;

    @NotNull
    private UUID memberId;

    @Min(1)
    private int quantity;
}