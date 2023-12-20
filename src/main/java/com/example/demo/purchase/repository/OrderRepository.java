package com.example.demo.purchase.repository;

import com.example.demo.purchase.model.Orders;
import com.example.demo.purchase.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    public Optional<Orders> findByIdx(Integer idx);
}
