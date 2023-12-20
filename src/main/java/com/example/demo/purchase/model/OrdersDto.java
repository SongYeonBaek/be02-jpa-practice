package com.example.demo.purchase.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdersDto {
    Integer idx;

    MemberDto memberDto;

    ProductDto productDto;
}
