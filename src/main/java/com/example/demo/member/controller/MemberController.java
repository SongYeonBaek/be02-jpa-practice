package com.example.demo.member.controller;

import com.example.demo.member.model.dto.MemberLoginReq;
import com.example.demo.member.model.dto.MemberSignupReq;
import com.example.demo.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "회원 컨트롤러", tags = "회원 API")
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    @ApiOperation(value = "회원 가입")
    public ResponseEntity signup( MemberSignupReq memberSignupReq) {

        return ResponseEntity.ok().body(memberService.signup(memberSignupReq));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity login( MemberLoginReq memberLoginReq) {

        return ResponseEntity.ok().body(memberService.login(memberLoginReq));
    }

}