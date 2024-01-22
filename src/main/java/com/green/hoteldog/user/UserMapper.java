package com.green.hoteldog.user;

import com.green.hoteldog.user.models.UserEntity;
import com.green.hoteldog.user.models.UserSignupDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int userSignup(UserSignupDto dto); // - 재웅
    UserEntity userEntityByUserEmail(String userEmail);// - 재웅
}
