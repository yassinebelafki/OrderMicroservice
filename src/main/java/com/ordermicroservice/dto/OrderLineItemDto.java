package com.ordermicroservice.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItemDto {

    private Long id;

    private String skuCode;

    private double price;

    private Integer quantity;


}
