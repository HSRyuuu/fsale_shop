package com.hsryuuu.flashsale.orders;

public enum OrderStatus {
    PENDING,    // 주문 접수됨
    COMPLETED,  // 결제·처리 완료
    CANCELLED,  // 주문 취소됨
    FAILED;     // 실패
    
}
