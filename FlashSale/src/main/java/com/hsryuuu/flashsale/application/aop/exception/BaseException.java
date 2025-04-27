package com.hsryuuu.flashsale.application.aop.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class BaseException extends RuntimeException {
    private HttpStatus status;
    private String errorMessage;
    private Throwable cause;
    private Object data;


    public BaseException(HttpStatus status, String errorMessage, Throwable cause, Object data) {
        this.status = status;
        this.errorMessage = errorMessage;
        this.cause = cause;
        this.data = data;
    }

    public BaseException(HttpStatus status) {
        this(status, "", null, null);
    }

    public BaseException(HttpStatus status, String errorMessage) {
        this(status, errorMessage, null, null);
    }

    public BaseException(HttpStatus status, String errorMessage, Object data) {
        this(status, errorMessage, null, data);
    }

    public BaseException(HttpStatus status, String errorMessage, Throwable cause) {
        this(status, errorMessage, cause, null);
    }

}
