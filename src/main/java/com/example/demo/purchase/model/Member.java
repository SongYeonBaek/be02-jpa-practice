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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idx;

    String email;
    String password;

    @OneToMany(mappedBy = "member")
    List<Orders> ordersList = new ArrayList<>();
}
