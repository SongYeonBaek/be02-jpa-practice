package com.example.demo.purchase.controller;

import com.example.demo.purchase.model.OrderReq;
import com.example.demo.purchase.repository.OrderRepository;
import com.example.demo.purchase.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.lang.reflect.Method;

@RestController
@RequestMapping("/order")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST,value =  "/create")
    public ResponseEntity create(@RequestBody OrderReq orderReq){
        orderService.create(orderReq);

        return ResponseEntity.ok().body("주문 성공");
    }

    @RequestMapping(method = RequestMethod.GET,value ="/list")
    public ResponseEntity list(){

        return ResponseEntity.ok().body(orderService.list());

    }


    @RequestMapping(method = RequestMethod.DELETE,value ="/delete")
    public ResponseEntity delete(){

        return ResponseEntity.ok().body("주문 삭제");

    }

}
