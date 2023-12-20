package com.example.demo.purchase.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idx;

    String name;
    String price;

    @OneToMany(mappedBy = "product")
    List<Orders> ordersList = new ArrayList<>();
}
