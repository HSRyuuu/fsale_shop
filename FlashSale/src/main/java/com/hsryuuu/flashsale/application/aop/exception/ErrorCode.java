package com.hsryuuu.flashsale.application.aop.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "요청에 문제가 있습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 데이터입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 문제가 발생했습니다."),
    BAD_REQUEST_ENUM(HttpStatus.BAD_REQUEST, "잘못된 enum 값 요청입니다."),
    REDIS_KEY_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, "Redis Key Not Found."),
    PRODUCT_OUT_OF_STOCK(HttpStatus.CONFLICT, "상품의 재고가 부족합니다."),
    FLASH_SALE_EVENT_OUT_OF_STOCK(HttpStatus.CONFLICT, "이벤트 재고가 소진되었습니다.");


    private final HttpStatus status;
    private final String message;
}
