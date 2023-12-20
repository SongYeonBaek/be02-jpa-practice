package com.example.demo.purchase.repository;

import com.example.demo.purchase.model.Member;
import com.example.demo.purchase.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public Optional<Product> findByIdx(Integer idx);
}
