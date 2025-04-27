package com.hsryuuu.flashsale.application.aop.exception;

import com.hsryuuu.flashsale.application.aop.apiresponse.StandardResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalCustomExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<StandardResponse<Object>> handleBaseException(BaseException e) {
        StandardResponse<Object> standardResponse = new StandardResponse<>(e.getStatus(), e.getErrorMessage());
        // 발생한 에러가 BaseException.cause 에 담겨있는 경우
        if (e.getCause() != null) {
            standardResponse.setMessage(e.getCause().getMessage());
        }
        if (e.getData() != null) {
            standardResponse.setData(e.getData());
        }
        log.error("Error Message: {}", e.getErrorMessage(), e);
        return ResponseEntity.status(e.getStatus()).body(standardResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse<Object>> handleUnknownException(Exception e) {
        StandardResponse<Object> standardResponse = new StandardResponse<>(e);
        log.error("Unknown Error: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(standardResponse);
    }
}
