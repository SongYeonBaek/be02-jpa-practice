package com.example.demo.purchase.controller;

import com.example.demo.purchase.model.MemberDto;
import com.example.demo.purchase.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(method = RequestMethod.POST,value =  "/signup")
    public ResponseEntity create(@RequestBody MemberDto memberDto){
        memberService.create(memberDto);
        return ResponseEntity.ok().body("ok");
    }

    @RequestMapping(method = RequestMethod.POST,value ="/login")
    public ResponseEntity login(@RequestBody MemberDto memberDto){

        return ResponseEntity.ok().body(memberService.login(memberDto));
    }
    @RequestMapping(method = RequestMethod.GET,value ="/read")
    public ResponseEntity read(Integer id){

        return ResponseEntity.ok().body(memberService.read(id));
    }
    @RequestMapping(method = RequestMethod.PATCH,value ="/update")
    public ResponseEntity update(MemberDto memberDto){
        memberService.update(memberDto);
        return ResponseEntity.ok().body("회원 정보 수정");

    }


    @RequestMapping(method = RequestMethod.DELETE,value ="/delete")
    public ResponseEntity delete(Integer id){
        memberService.delete(id);
        return ResponseEntity.ok().body("회원 탈퇴");

    }

}
