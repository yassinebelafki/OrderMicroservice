package com.ordermicroservice.service;


import com.ordermicroservice.dto.OrderRequest;
import com.ordermicroservice.model.MyOrder;
import com.ordermicroservice.model.OrderLineItem;
import com.ordermicroservice.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepo orderRepo;

    public void placeOrder(OrderRequest orderRequest) {
        MyOrder myOrder = MyOrder.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(orderRequest.getOrderLineItemDtos().stream().map(
                        orderLineItemDto -> { return OrderLineItem.builder()
                                        .skuCode(orderLineItemDto.getSkuCode())
                                                .price(orderLineItemDto.getPrice())
                                                        .quantity(orderLineItemDto.getQuantity()).build();
                        }
                ).toList())
                .build();
        orderRepo.save(myOrder);
    }
}
