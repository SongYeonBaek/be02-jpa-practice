package com.example.demo.member.controller;

import com.example.demo.member.model.Member;
import com.example.demo.member.model.dto.MemberLoginReq;
import com.example.demo.member.service.KakaoService;
import com.example.demo.member.service.MemberService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;
    private KakaoService kakaoService;
    private AuthenticationManager authenticationManager;

    public MemberController(MemberService memberService, KakaoService kakaoService, AuthenticationManager authenticationManager) {
        this.memberService = memberService;
        this.kakaoService = kakaoService;
        this.authenticationManager = authenticationManager;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login(@RequestBody MemberLoginReq memberLoginReq) {

        return ResponseEntity.ok().body(memberService.login(memberLoginReq));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/kakao")
    // 인가 코드 받아오는 코드
    public ResponseEntity kakao(String code) {
        System.out.println(code);
        /////////////////////////////////

        // 인가 코드로 토큰 받아오는 코드
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "9891162dc320dcbae5ff25e61dda6523");
        params.add("redirect_uri", "http://localhost:8080/member/kakao");
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> response = restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                request,
                Object.class
        );
        System.out.println(response);

        String result = "" + response;
        String accessToken = result.split(",")[1].split("=")[1];
        ///////////////////////////////////////////////////////////////

        // 토큰으로 사용자 정보 받아오는 코드
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
        headers2.add(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity request2 = new HttpEntity<>(headers2);
        RestTemplate restTemplate2 = new RestTemplate();
        ResponseEntity<Object> response2 = restTemplate2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request2,
                Object.class
        );

        System.out.println(response2);

        String result2 = "" + response2.getBody();
        String userName = result2.split("nickname=")[1].split("}")[0];
        //////////////////////////////////////////////

        // 가져온 사용자 정보로 DB 확인
        Member member = memberService.getMemberByEmail(userName);
        if(member == null) {
            // DB에 없으면 회원 가입
            memberService.kakaoSignup(userName);
        }

        // 로그인 처리(JWT 토큰 발급)
        return ResponseEntity.ok().body(memberService.kakaoLogin(userName));
    }
}