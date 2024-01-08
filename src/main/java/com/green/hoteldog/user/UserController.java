package com.green.hoteldog.user;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    @PostMapping("/signup")
    public ResVo postUserSignup(@RequestBody UserSignUpDto dto){
        return service.signup(dto);
    }

    @PostMapping("/signin")
    public UserSigninVo postUserSignin(@RequestBody UserSigninDto dto){
        return service.signin(dto);
    }


    @GetMapping("/user_pk")
    public UserInfoVo getUserInfo(UserInfoDto dto){
        return service.getUserInfo(dto);
    }
}