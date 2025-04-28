package com.hsryuuu.flashsale.stock.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.UUID;


@RequiredArgsConstructor
@Component
public class RedisStockRecoveryRegistrar {

    private final StockRedisService stockRedisService;

    /**
     * 트랜잭션이 롤백되면 Redis 재고를 복구하도록 콜백을 등록합니다.
     *
     * @param eventId  FlashSaleEvent ID
     * @param quantity 복구할 수량
     */
    public void registerRecovery(UUID eventId, int quantity) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCompletion(int status) {
                if (status == STATUS_ROLLED_BACK) {
                    stockRedisService.incrementStock(eventId, quantity);
                }
            }
        });
    }
}