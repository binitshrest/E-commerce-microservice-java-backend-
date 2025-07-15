package com.binitshrestha.inventory_service.service;

import com.binitshrestha.inventory_service.dto.InventoryResponse;
import com.binitshrestha.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
    @SneakyThrows //Don't use in prod, used for exception
    public List<InventoryResponse> isInStock(List<String> skuCode){
        log.info("Wait Started");
        //sleep thread of 10 sec order service call inventory to trigger circuit breaker than fall back mechanism
//        Thread.sleep(10000);
        log.info("Wait End");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();
    }
}
