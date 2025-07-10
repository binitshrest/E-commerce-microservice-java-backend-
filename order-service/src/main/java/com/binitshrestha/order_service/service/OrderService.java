package com.binitshrestha.order_service.service;

import com.binitshrestha.order_service.dto.OrderLineItemsDto;
import com.binitshrestha.order_service.dto.OrderRequest;
import com.binitshrestha.order_service.model.Order;
import com.binitshrestha.order_service.model.OrderLineItems;
import com.binitshrestha.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest){
      Order order = Order.builder()
              .orderNumber(UUID.randomUUID().toString())
              .build();
      List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
              .stream()
              .map(this::mapToDto)
              .toList();
      order.setOrderLineItemsList(orderLineItems);

      orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }
}
