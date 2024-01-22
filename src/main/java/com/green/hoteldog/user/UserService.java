package com.green.hoteldog.user;

import com.green.hoteldog.common.AppProperties;
import com.green.hoteldog.common.CookieUtils;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.security.JwtTokenProvider;
import com.green.hoteldog.security.Myprincipal;
import com.green.hoteldog.user.models.UserEntity;
import com.green.hoteldog.user.models.UserSigninDto;
import com.green.hoteldog.user.models.UserSigninVo;
import com.green.hoteldog.user.models.UserSignupDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AppProperties appProperties;
    private final CookieUtils cookie;
    //유저 회원가입
    public ResVo userSignup (UserSignupDto dto){
        String pw = passwordEncoder.encode(dto.getUpw());
        dto.setUpw(pw);
        int result = mapper.userSignup(dto);
        return new ResVo(result);
    }
    //유저 로그인
    public UserSigninVo userSignin(HttpServletResponse response, HttpServletRequest request, UserSigninDto dto){
        UserSigninVo vo = new UserSigninVo();
        UserEntity userEntity = mapper.userEntityByUserEmail(dto.getUserEmail());
        if(passwordEncoder.matches(dto.getUpw(),userEntity.getUpw())){
            Myprincipal myprincipal = new Myprincipal(userEntity.getUserPk());
            String at = tokenProvider.generateAccessToken(myprincipal);
            String rt = tokenProvider.generateRefreshToken(myprincipal);

            int rtCookieMaxAge = (int)appProperties.getJwt().getRefreshTokenExpiry() / 1000;
            cookie.deleteCookie(response,"rt");
            cookie.setCookie(response,"rt",rt,rtCookieMaxAge);
            vo.setNm(userEntity.getNm());
            vo.setUserPk(userEntity.getUserPk());
            vo.setUserTypePk(userEntity.getUserTypePk());
            vo.setAccessToken(at);
            vo.setNickname(userEntity.getNickname());
            return vo;
        }
        return null;
    }
    public ResVo checkNickname(String nickname){
        List<UserEntity> userEntityList = mapper.selUserEntity();
        for(UserEntity entity : userEntityList){
            if(entity.getNickname().equals(nickname)){
                return new ResVo(1);
            }
        }
        return new ResVo(0);
    }
}
