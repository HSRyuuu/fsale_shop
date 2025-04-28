package com.hsryuuu.flashsale.application.aop.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class GlobalException extends RuntimeException {
    private HttpStatus status;
    private String errorMessage;
    private Throwable cause;
    private Object data;

    public GlobalException(HttpStatus status, String errorMessage, Throwable cause, Object data) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.cause = cause;
        this.data = data;
    }

    public GlobalException(ErrorCode errorCode, Throwable cause, Object data) {
        this.status = errorCode.getStatus();
        this.errorMessage = errorCode.getMessage();
        this.cause = cause;
        this.data = data;
    }

    public GlobalException(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.errorMessage = errorCode.getMessage();
    }


    public GlobalException(HttpStatus status, String errorMessage) {
        this(status, errorMessage, null, null);
    }

    public static GlobalException defaultNotFound(String additionalInfo) {
        String errorMessage = ErrorCode.NOT_FOUND.getMessage() + "=>" + additionalInfo;
        return new GlobalException(HttpStatus.NOT_FOUND, errorMessage, null, null);
    }

}
