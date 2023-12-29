package com.example.demo.member.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Member  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(nullable = false, length = 30)
    private String email;
    @Column(nullable = false, length = 30)
    private String password;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 50)
    private String addr;
    @Column(nullable = false, length = 15)
    private String pNum;

    private String socialLogin = "x";



}
