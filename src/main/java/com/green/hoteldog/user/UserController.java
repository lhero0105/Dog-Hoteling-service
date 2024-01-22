package com.green.hoteldog.user;

import com.green.hoteldog.common.RedisUtil;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.user.models.UserSigninDto;
import com.green.hoteldog.user.models.UserSigninVo;
import com.green.hoteldog.user.models.UserSignupDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;
    private final RedisUtil redisUtil;

    //유저 회원가입
    @PostMapping("/signup")
    public ResVo userSignup(@RequestBody UserSignupDto dto){
        ResVo vo = new ResVo(0);
        if(dto.getUserEmail().equals(dto.getEmailResponseVo().getEmail()) && dto.getEmailResponseVo().getResult() == 1){
            vo = service.userSignup(dto);
        }
        if(vo.getResult() == 1){
            redisUtil.deleteData(dto.getUserEmail());
        }
        return vo;
    }
    //유저 로그인
    @PostMapping("/signin")
    public UserSigninVo userSignin(HttpServletResponse response, HttpServletRequest request, @RequestBody UserSigninDto dto){
        return service.userSignin(response,request,dto);
    }

    //닉네임 중복체크
    @PostMapping("/nickname-check")
    public ResVo nicknameCheck(String nickname){
        return service.checkNickname(nickname);
    }


}