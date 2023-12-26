package com.example.demo.board.model;

import lombok.Data;

@Data
public class BoardDto {
    private int Product_ID;
    private String Image;
    private String Name;
    private String Information;

    private String Seller_ID;
    private int Category_ID;
}
