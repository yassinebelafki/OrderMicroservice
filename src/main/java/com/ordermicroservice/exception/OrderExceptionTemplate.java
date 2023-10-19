package com.ordermicroservice.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderExceptionTemplate {
private int status;
private String message;
private long timeStamp;
}
