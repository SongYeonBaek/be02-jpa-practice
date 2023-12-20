package com.example.demo.purchase.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idx;

    @ManyToOne
    @JoinColumn(name = "Member_idx")
    Member member;

    @ManyToOne
    @JoinColumn(name = "Product_idx")
    Product product;
}
