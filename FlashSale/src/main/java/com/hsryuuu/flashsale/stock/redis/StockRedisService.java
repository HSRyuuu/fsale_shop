package com.hsryuuu.flashsale.stock.redis;


import com.hsryuuu.flashsale.application.aop.exception.ErrorCode;
import com.hsryuuu.flashsale.application.aop.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class StockRedisService {

    private static final String STOCK_KEY_PREFIX = "flash:event:stock:";

    private final RedisTemplate<String, Long> redisTemplate;

    public void initStock(UUID eventId, long totalQuantity) {
        String key = STOCK_KEY_PREFIX + eventId;
        this.setValue(key, (long) totalQuantity);
    }

    public long decrementStock(UUID eventId, long quantity) {
        return this.decrement(this.getKey(eventId), quantity);
    }

    public long incrementStock(UUID eventId, long quantity) {
        return this.increment(this.getKey(eventId), quantity);
    }

    private String getKey(UUID eventId) {
        return STOCK_KEY_PREFIX + eventId;
    }

    /**
     * INSERT
     */
    private void setValue(String key, Long value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * value 만큼 감소
     */
    private long decrement(String key, long value) {
        Long result = redisTemplate.opsForValue().decrement(key, value);
        if (result == null) {
            throw new GlobalException(ErrorCode.REDIS_KEY_NOT_FOUND);
        }
        return result;
    }

    private long increment(String key, long value) {
        Long result = redisTemplate.opsForValue().increment(key, value);
        if (result == null) {
            throw new GlobalException(ErrorCode.REDIS_KEY_NOT_FOUND);
        }
        return result;
    }

    /**
     * key 값 조회
     */
    private long getValue(String key) {
        Long result = redisTemplate.opsForValue().get(key);
        return result == null ? 0L : result;
    }

    /**
     * key 만료 설정
     */
    private void expire(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }


}
