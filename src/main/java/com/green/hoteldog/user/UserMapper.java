package com.green.hoteldog.user;

import com.green.hoteldog.user.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserSignUpDto dto);
    UserEntity selUser(UserSelDto dto);
    UserInfoVo selUserInfo(UserInfoDto dto);
}
