package com.hsryuuu.flashsale.orders;


import com.hsryuuu.flashsale.orders.dto.OrderDto;
import com.hsryuuu.flashsale.orders.dto.PlaceOrderRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "주문 API")
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@RestController
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "플래시 세일 이벤트에 대한 주문을 생성")
    @PostMapping
    public OrderDto placeOrder(@Valid @RequestBody PlaceOrderRequest req) {
        return orderService.placeOrder(req.getEventId(), req.getMemberId(), req.getQuantity());
    }
}
