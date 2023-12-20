package com.example.demo.purchase.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class MemberDto {
    Integer idx;

    String email;
    String password;

    List<OrdersDto> ordersList = new ArrayList<>();

}
