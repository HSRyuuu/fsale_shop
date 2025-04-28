package com.hsryuuu.flashsale.orders;

import com.hsryuuu.flashsale.application.aop.exception.ErrorCode;
import com.hsryuuu.flashsale.application.aop.exception.GlobalException;
import com.hsryuuu.flashsale.event.flashsale.FlashSaleEvent;
import com.hsryuuu.flashsale.event.flashsale.FlashSaleEventRepository;
import com.hsryuuu.flashsale.member.Member;
import com.hsryuuu.flashsale.member.MemberRepository;
import com.hsryuuu.flashsale.orders.dto.OrderDto;
import com.hsryuuu.flashsale.orders.utils.OrderMapper;
import com.hsryuuu.flashsale.stock.redis.RedisStockRecoveryRegistrar;
import com.hsryuuu.flashsale.stock.redis.StockRedisService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final FlashSaleEventRepository flashSaleEventRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final StockRedisService stockRedisService;
    private final RedisStockRecoveryRegistrar redisRecovery;

    @Transactional
    public OrderDto placeOrder(@NotNull UUID eventId, @NotNull UUID memberId, @Min(1) int quantity) {
        FlashSaleEvent event = flashSaleEventRepository.findById(eventId)
                .orElseThrow(() -> GlobalException.defaultNotFound("FlashSaleEvent"));
        // redis 차감
        long remaining = stockRedisService.decrementStock(eventId, quantity);
        if (remaining < 0) {
            stockRedisService.incrementStock(eventId, quantity);
            throw new GlobalException(ErrorCode.FLASH_SALE_EVENT_OUT_OF_STOCK);
        }
        // 트랜잭션 롤백 시 redis 잔고 복구
        redisRecovery.registerRecovery(eventId, quantity);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> GlobalException.defaultNotFound("Member"));

        // 주문 생성
        Orders saved = orderRepository.save(Orders.builder()
                .member(member)
                .event(event)
                .quantity(quantity)
                .orderPrice(event.getSalePrice().multiply(BigDecimal.valueOf(quantity)))
                .status(OrderStatus.PENDING)
                .build());

        return OrderMapper.fromEntity(saved);
    }
}
