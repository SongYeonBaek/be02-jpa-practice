package com.example.demo.purchase.repository;

import com.example.demo.purchase.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer>{
    public Optional<Member> findByIdx(Integer idx);
    public Optional<Member> findByEmail(String email);
}
