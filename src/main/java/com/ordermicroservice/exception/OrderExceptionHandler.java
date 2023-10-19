package com.ordermicroservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<OrderExceptionTemplate> allExceptions(Exception exception){
        OrderExceptionTemplate exp = OrderExceptionTemplate.builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis())
                .build();
        return new ResponseEntity<>(exp,HttpStatus.BAD_REQUEST);
    }

}
