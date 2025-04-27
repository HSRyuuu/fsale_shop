package com.hsryuuu.flashsale.event.flashsale;

import com.hsryuuu.flashsale.event.flashsale.dto.CreateFlashSaleEventRequest;
import com.hsryuuu.flashsale.event.flashsale.dto.FlashSaleEventDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "[Flash Sale Event API] 세일 이벤트 생성")
@RequiredArgsConstructor
@RestController("/api/flash-sale-event")
public class FlashSaleEventController {

    private final FlashSaleEventService flashSaleEventService;

    @Operation(summary = "Flash Sale Event 생성")
    @PostMapping
    public FlashSaleEventDto create(@RequestBody CreateFlashSaleEventRequest request) {
        return flashSaleEventService.createFlashSaleEvent(request);
    }
}
