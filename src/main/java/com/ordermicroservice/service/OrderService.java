package com.ordermicroservice.service;


import com.ordermicroservice.dto.InventroyResponse;
import com.ordermicroservice.dto.OrderRequest;
import com.ordermicroservice.exception.OrderException;
import com.ordermicroservice.model.MyOrder;
import com.ordermicroservice.model.OrderLineItem;
import com.ordermicroservice.repository.OrderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderRepo orderRepo;
    private  final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest) throws OrderException {
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
        //we should check if the product in the order exist in the stock so we will send an synchronos request
        //to the inventory service to see if one of the products in the order exist in the stock
        List<String> skuCodes=myOrder.getOrderLineItems().stream()
                        .map(OrderLineItem::getSkuCode).toList();


        InventroyResponse[] inventroyResponses = webClientBuilder.build().get()
                        .uri("http://inventory-service/api/inventory",uriBuilder ->
                             uriBuilder.queryParam("skuCode",skuCodes).build()
                        )
                        .retrieve()
                        .bodyToMono(InventroyResponse[].class)
                        .block();
        if (inventroyResponses != null){
             boolean isAllProductsInStock = Arrays.stream(inventroyResponses).anyMatch(InventroyResponse::isExist);
             if (isAllProductsInStock){
                 orderRepo.save(myOrder);
             }
             else {
                 throw new OrderException("One of the products does not exist or have the enough amount in stock");
             }
         }
        else {
                throw new OrderException("One of the products does not exist or have the enough amount in stock");
        }

    }
}
