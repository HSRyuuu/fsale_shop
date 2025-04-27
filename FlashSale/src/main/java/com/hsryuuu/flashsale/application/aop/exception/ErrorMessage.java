package com.hsryuuu.flashsale.application.aop.exception;

/**
 * 에러 메시지 상수 클래스
 */
public final class ErrorMessage {
    public static final String BAD_REQUEST = "BAD_REQUEST: 요청에 문제가 있습니다.";
    public static final String NOT_FOUND = "NOT_FOUND: 존재하지 않는 데이터입니다.";
    public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
    public static final String BAD_REQUEST_ENUM = "BAD_REQUEST_ENUM: 잘못된 enum 값 요청입니다.";
    private ErrorMessage() {
    }

}
