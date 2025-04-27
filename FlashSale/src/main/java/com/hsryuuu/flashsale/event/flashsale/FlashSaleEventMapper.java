package com.hsryuuu.flashsale.event.flashsale;

import com.hsryuuu.flashsale.event.flashsale.dto.CreateFlashSaleEventRequest;
import com.hsryuuu.flashsale.event.flashsale.dto.FlashSaleEventDto;
import com.hsryuuu.flashsale.product.Product;
import com.hsryuuu.flashsale.product.ProductMapper;

public final class FlashSaleEventMapper {
    private FlashSaleEventMapper() {
    }

    public static FlashSaleEvent toEntity(CreateFlashSaleEventRequest request, Product product) {
        return FlashSaleEvent.builder()
                .product(product)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .salePrice(request.getSalePrice())
                .totalQuantity(request.getTotalQuantity())
                .build();
    }

    public static FlashSaleEventDto fromEntity(FlashSaleEvent flashSaleEvent) {
        return FlashSaleEventDto.builder()
                .id(flashSaleEvent.getId())
                .productId(flashSaleEvent.getProduct().getId())
                .startTime(flashSaleEvent.getStartTime())
                .endTime(flashSaleEvent.getEndTime())
                .salePrice(flashSaleEvent.getSalePrice())
                .totalQuantity(flashSaleEvent.getTotalQuantity())
                .createdAt(flashSaleEvent.getCreatedAt())
                .updatedAt(flashSaleEvent.getUpdatedAt())
                .product(ProductMapper.fromEntity(flashSaleEvent.getProduct()))
                .build();
    }
}
