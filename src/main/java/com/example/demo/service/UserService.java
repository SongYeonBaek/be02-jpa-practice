package com.example.demo.service;

import com.example.demo.model.UserLoginDto;
import com.example.demo.model.UserSignUpDto;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public boolean login(UserLoginDto userLoginDto){
        if(userLoginDto.getId().equals("test01") && userLoginDto.getPw().equals("qwer1234")){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean signUp(UserSignUpDto userSignUpDto){
        if(userSignUpDto.getName().equals("bsy") && userSignUpDto.getId().equals("test01")
                && userSignUpDto.getPw().equals("qwer1234") && userSignUpDto.getEmail().equals("test01@naver.com")){
            return true;
        }
        else {
            return false;
        }
    }
}
