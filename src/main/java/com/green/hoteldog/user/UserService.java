package com.green.hoteldog.user;

import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.ResVo;

import com.green.hoteldog.user.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    // 회원가입
    public ResVo signup(UserSignUpDto dto) {
        String salt = BCrypt.gensalt();
        String hashedPw = BCrypt.hashpw(dto.getUpw(), salt);

        UserSignUpDto upDto = new UserSignUpDto();
        upDto.setUid(dto.getUid());
        upDto.setUpw(hashedPw);
        upDto.setNm(dto.getNm());
        upDto.setPic(dto.getPic());
        upDto.setNickName(dto.getNickName());
        upDto.setPhoneNum(dto.getPhoneNum());


        if (dto.getUserTypePk() == 1) {
            upDto.setUserTypePk(Const.NORMAL_USER);
        } else if (dto.getUserTypePk() == 2) {
            upDto.setUserTypePk(Const.BUSINESS_USER);
        } else {
            return new ResVo(-1);
        }

        int affectedRows = mapper.insUser(upDto);

        return new ResVo(upDto.getUserPk());
    }

    // 로그인
    public UserSigninVo signin(UserSigninDto dto) {
        UserSelDto selDto = new UserSelDto();
        selDto.setUid(dto.getUid());
        UserEntity entity = mapper.selUser(selDto);
        if (entity == null) {
            return UserSigninVo.builder().result(Const.LOGIN_NO_UID).build();

        } else if (!BCrypt.checkpw(dto.getUpw(), entity.getUpw())) {
            return UserSigninVo.builder().result(Const.LOGIN_DIFF_UPW).build();
        }
        return UserSigninVo.builder()
                .result((entity.getUserPk()))
                .nickName(entity.getNm())
                .pic(entity.getPic())
                .build();
    }

    // 유저 프로필 찾기
    public UserInfoVo getUserInfo(UserInfoDto dto){
        return mapper.selUserInfo(dto);
    }

}
