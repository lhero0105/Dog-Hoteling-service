package com.green.hoteldog.user;

import com.green.hoteldog.user.models.UserEntity;
import com.green.hoteldog.user.models.UserSignupDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int userSignup(UserSignupDto dto);
    UserEntity userEntityByUserEmail(String userEmail);
    List<UserEntity> selUserEntity();
}
