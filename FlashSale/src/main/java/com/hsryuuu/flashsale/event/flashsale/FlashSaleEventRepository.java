package com.hsryuuu.flashsale.event.flashsale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FlashSaleEventRepository extends JpaRepository<FlashSaleEvent, UUID> {
}
