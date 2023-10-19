package com.ordermicroservice.controller;


import com.ordermicroservice.dto.OrderRequest;
import com.ordermicroservice.exception.OrderException;
import com.ordermicroservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) throws OrderException {
        orderService.placeOrder(orderRequest);
        return "Your Order Is Placed !";
    }


}
