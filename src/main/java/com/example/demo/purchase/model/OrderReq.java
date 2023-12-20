package com.example.demo.purchase.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderReq {
    private Integer memberId;
    private Integer productId;
}
