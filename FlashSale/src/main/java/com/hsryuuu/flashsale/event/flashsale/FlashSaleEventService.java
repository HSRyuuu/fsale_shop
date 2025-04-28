package com.hsryuuu.flashsale.event.flashsale;

import com.hsryuuu.flashsale.application.aop.exception.ErrorCode;
import com.hsryuuu.flashsale.application.aop.exception.GlobalException;
import com.hsryuuu.flashsale.event.flashsale.dto.CreateFlashSaleEventRequest;
import com.hsryuuu.flashsale.event.flashsale.dto.FlashSaleEventDto;
import com.hsryuuu.flashsale.product.Product;
import com.hsryuuu.flashsale.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class FlashSaleEventService {

    private final ProductRepository productRepository;
    private final FlashSaleEventRepository flashSaleEventRepository;

    @Transactional
    public FlashSaleEventDto createFlashSaleEvent(CreateFlashSaleEventRequest request) {
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> GlobalException.defaultNotFound("상품 (Product)"));
        if (product.getStockQuantity() < request.getTotalQuantity()) {
            throw new GlobalException(ErrorCode.PRODUCT_OUT_OF_STOCK);
        }
        FlashSaleEvent saved = flashSaleEventRepository.save(FlashSaleEventMapper.toEntity(request, product));
        return FlashSaleEventMapper.fromEntity(saved);
    }

}
