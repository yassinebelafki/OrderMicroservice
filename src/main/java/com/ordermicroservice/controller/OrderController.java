package com.ordermicroservice.controller;


import com.ordermicroservice.dto.OrderRequest;
import com.ordermicroservice.exception.OrderException;
import com.ordermicroservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @CircuitBreaker(name = "inventory",fallbackMethod = "InventoryBackUpMethode")
//    @TimeLimiter(name = "inventory")
//    @Retry(name = "inventory")
    public String placeOrder(@RequestBody OrderRequest orderRequest) throws OrderException {
        orderService.placeOrder(orderRequest);
        return  "Your Order Is Placed !";
    }


//    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) throws OrderException {
//        orderService.placeOrder(orderRequest);
//        return CompletableFuture.supplyAsync(()-> "Your Order Is Placed !");
//    }



//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public CompletableFuture<String> InventoryBackUpMethode(OrderRequest orderRequest,Exception exception){
//        return CompletableFuture.supplyAsync(()-> "ooops , something went wrong please try later!");
//
//    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String InventoryBackUpMethode(OrderRequest orderRequest,Exception exception){
        return  "ooops , something went wrong please try later! ";

    }


}
