package com.example.demo.purchase.service;

import com.example.demo.purchase.model.*;
import com.example.demo.purchase.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void create(ProductDto productDto){
        //입력으로 들어온 MemberDto를 Member Entity로 바꿔서 DB에 저장한다
        List<OrdersDto> ordersList = productDto.getOrdersList();

       Product product = Product.builder()
                .idx(productDto.getIdx())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build();

        productRepository.save(product);
    }

    public List<ProductDto> list(){
        //Product 리스트를 모두 가져옴
        List<Product> list = productRepository.findAll();
        //ProductDto로 타입을 변환해서 result에 저장함
        List<ProductDto> result = new ArrayList<>();

        for(Product product : list){
            //Orders에서 OrdersDto로 타입 변환
            List<Orders> orders = product.getOrdersList();
            List<OrdersDto> ordersDtos = new ArrayList<>();

            for(Orders order :orders){
                Product product1 = order.getProduct();
                Member member1 = order.getMember();
                OrdersDto ordersDto = OrdersDto.builder()
                        .idx(order.getIdx())
                        .productDto(ProductDto.builder().idx(product1.getIdx()).build())
                        .memberDto(MemberDto.builder().idx(member1.getIdx()).build())
                        .build();

                ordersDtos.add(ordersDto);
            }

            result.add(ProductDto.builder()
                            .idx(product.getIdx())
                            .name(product.getName())
                            .price(product.getPrice())
                            .ordersList(ordersDtos)
                    .build());
        }

        return result;
    }
     public ProductDto read(Integer id){
        Optional<Product> product = productRepository.findById(id);

        if(product.isPresent()){
            //Product 안에 있는 Orders를 OrdersDto로 바꿔준다
            List<OrdersDto> result = new ArrayList<>();

            for(Orders orders :product.get().getOrdersList()){

                Product product1 = orders.getProduct();
                Member member1 = orders.getMember();

                result.add(OrdersDto.builder()
                        .idx(orders.getIdx())
                        .memberDto(MemberDto.builder().idx(member1.getIdx()).build())
                        .productDto(ProductDto.builder().idx(product1.getIdx()).build())
                        .build());
            }

            return ProductDto.builder()
                    .idx(product.get().getIdx())
                    .name(product.get().getName())
                    .price(product.get().getPrice())
                    .ordersList(result)
                    .build();
        }
        else{
            return null;
        }
    }

    public void update(ProductDto productDto){
        //MemeberDto를 Member로 바꿔서 DB에 저장

        Product product = Product.builder()
                .idx(productDto.getIdx())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .build();
        productRepository.save(product);
    }

    public void delete(Integer id){
        productRepository.delete(Product.builder().idx(id).build());
    }
}
