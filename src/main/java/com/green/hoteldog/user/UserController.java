package com.green.hoteldog.user;

import com.green.hoteldog.common.RedisUtil;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.user.models.UserSigninDto;
import com.green.hoteldog.user.models.UserSigninVo;
import com.green.hoteldog.user.models.UserSignupDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;
    private final RedisUtil redisUtil;

    @PostMapping("/signout")
    public ResVo postSignout(HttpServletResponse response){
        return service.signout(response);
    }

    //유저 회원가입
    @PostMapping("/signup")
    public ResVo userSignup(@RequestBody @Valid UserSignupDto dto){
        ResVo vo = new ResVo(0);
        if(dto.getEmailResponseVo().getResult() == 1){
            vo = service.userSignup(dto);
        }
        if(vo.getResult() == 1){
            redisUtil.deleteData(dto.getEmailResponseVo().getEmail());
        }
        return vo;
    }
    //유저 로그인
    @PostMapping("/signin")
    public UserSigninVo userSignin(HttpServletResponse response,HttpServletRequest request, @RequestBody @Valid UserSigninDto dto){
        return service.userSignin(response,request,dto);
    }

    //닉네임 중복체크
    @GetMapping("/nickname-check")
    public ResVo nicknameCheck(String nickname){
        return service.checkNickname(nickname);
    }


}