package com.example.demo.member.model.dto;

import com.example.demo.member.model.Member;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Builder
@Data
public class MemberSignupReq {
    private String email;
    private String password;
    private String name;
    private String addr;
    private String pNum;
    private String socialLogin;

    public Member toEntity(){
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .addr(this.addr)
                .pNum(this.pNum)
                .socialLogin(this.socialLogin)
                .build();
    }
}
