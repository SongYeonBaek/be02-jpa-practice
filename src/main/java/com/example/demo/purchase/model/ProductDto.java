package com.example.demo.purchase.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ProductDto {
    Integer idx;

    String name;
    String price;

    List<OrdersDto> ordersList = new ArrayList<>();

}
