package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.model.UserLoginDto;
import com.example.demo.model.UserSignUpDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<Object> login(UserLoginDto userLoginDto){
        if(userService.login(userLoginDto)) {
            return ResponseEntity.ok().body("로그인 성공");
        }
        else {
            return ResponseEntity.badRequest().body("로그인 실패");
        }

//        Builder 예시
//        UserLoginDto user1 = new UserLoginDto();
//        UserLoginDto user2 = new UserLoginDto("test01", "qwer1234");
//        UserLoginDto user3 = UserLoginDto.builder().id("test01").pw("qwer1234").build();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<Object> signup(UserSignUpDto userSignUpDto){
        System.out.println("이름  = " + userSignUpDto.getName());
        System.out.println("id = " + userSignUpDto.getId());
        System.out.println("pw = " + userSignUpDto.getPw());
        System.out.println("email = " + userSignUpDto.getEmail());


        if(userService.signUp(userSignUpDto)){
            return ResponseEntity.ok().body("회원가입 성공");
        }
        else {
            return ResponseEntity.badRequest().body("회원가입 실패");
        }
//        return "user/signup";
    }

}
