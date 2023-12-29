package com.example.demo.member.service;

import com.example.demo.member.model.Member;
import com.example.demo.member.model.dto.MemberLoginReq;
import com.example.demo.member.model.dto.MemberSignupReq;
import com.example.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public String signup(MemberSignupReq memberSignupReq){
        Member member = memberSignupReq.toEntity();
        memberRepository.save(member);
        return "ok";
    }

    public String login(MemberLoginReq memberLoginReq) {
        Optional<Member> member = memberRepository.findByEmail(memberLoginReq.getEmail());
        if(member.isPresent()){
            return "ok";
        }
        return "fail";
    }

}
