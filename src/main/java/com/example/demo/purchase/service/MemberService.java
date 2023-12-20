package com.example.demo.purchase.service;

import com.example.demo.purchase.model.*;
import com.example.demo.purchase.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void create(MemberDto memberDto){
        //입력으로 들어온 MemberDto를 Member Entity로 바꿔서 DB에 저장한다
        List<OrdersDto> ordersList = memberDto.getOrdersList();

        Member member = Member.builder()
                .idx(memberDto.getIdx())
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .build();

        memberRepository.save(member);
    }

    public MemberDto login(MemberDto memberDto){
        Optional<Member> member = memberRepository.findByEmail(memberDto.getEmail());
        if(member.isPresent()){
            return MemberDto.builder()
                    .email(memberDto.getEmail())
                    .password(memberDto.getPassword())
                    .build();
        }
        else{
            return null;
        }
    }

    public MemberDto read(Integer id){
        Optional<Member> member = memberRepository.findByIdx(id);

        if(member.isPresent()){
            List<OrdersDto> result = new ArrayList<>();

            for(Orders orders :member.get().getOrdersList()){
                Product product1 = orders.getProduct();
                Member member1 = orders.getMember();

                result.add(OrdersDto.builder()
                        .idx(orders.getIdx())
                        .memberDto(MemberDto.builder().idx(member1.getIdx()).build())
                        .productDto(ProductDto.builder().idx(product1.getIdx()).build())
                        .build());
            }

            return MemberDto.builder()
                    .idx(member.get().getIdx())
                    .email(member.get().getEmail())
                    .password(member.get().getPassword())
                    .ordersList(result)
                    .build();
        }
        else{
            return null;
        }
    }

    public void update(MemberDto memberDto){
        //MemeberDto를 Member로 바꿔서 DB에 저장

        Member member = Member.builder()
                .idx(memberDto.getIdx())
                .email(memberDto.getEmail())
                .password(memberDto.getPassword())
                .build();
        memberRepository.save(member);
    }

    public void delete(Integer id){
        memberRepository.delete(Member.builder().idx(id).build());
    }
}
