package com.example.demo.purchase.service;

import com.example.demo.purchase.model.*;
import com.example.demo.purchase.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void create(OrderReq orderReq){
        Orders orders = Orders.builder()
                .member(Member.builder().idx(orderReq.getMemberId()).build())
                .product(Product.builder().idx(orderReq.getProductId()).build())
                .build();
        orderRepository.save(orders);

}

    public List<OrdersDto> list(){
        List<Orders> list = orderRepository.findAll();

        List<OrdersDto> result = new ArrayList<>();

        for(Orders orders : list){

            Member member= orders.getMember();
            MemberDto memberDto = MemberDto.builder()
                    .email(member.getEmail())
                    .password(member.getPassword())
                    .build();

            Product product = orders.getProduct();
            ProductDto productDto  = ProductDto.builder()
                    .name(product.getName())
                    .price(product.getPrice())
                    .build();

            result.add(OrdersDto.builder()
                            .memberDto(memberDto)
                            .productDto(productDto)
                    .build());
        }

        return result;
    }

    public void delete(Integer id){
        orderRepository.delete(Orders.builder().idx(id).build());
    }

}
