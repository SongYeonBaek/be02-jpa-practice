package com.example.demo.purchase.controller;

import com.example.demo.purchase.model.ProductDto;
import com.example.demo.purchase.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST,value =  "/create")
    public ResponseEntity create(ProductDto productDto){
        productService.create(productDto);
        return ResponseEntity.ok().body("상품 등록 성공");
    }

    @RequestMapping(method = RequestMethod.GET,value ="/list")
    public ResponseEntity list(){
        return ResponseEntity.ok().body(productService.list());

    }

    @RequestMapping(method = RequestMethod.GET,value ="/read")
    public ResponseEntity read(Integer id){
        return ResponseEntity.ok().body(productService.read(id));

    }

    @RequestMapping(method = RequestMethod.PATCH,value ="/update")
    public ResponseEntity update(ProductDto productDto){
        productService.update(productDto);
        return ResponseEntity.ok().body("상품 수정");
    }

    @RequestMapping(method = RequestMethod.DELETE,value ="/delete")
    public ResponseEntity delete(Integer id){
        productService.delete(id);
        return ResponseEntity.ok().body("상품 삭제");

    }

}