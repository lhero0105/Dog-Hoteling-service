package com.green.hoteldog.user;

import com.green.hoteldog.user.models.UserAddressInfo;
import com.green.hoteldog.user.models.UserInfo;
import com.green.hoteldog.user.models.UserSignupDto;
import com.green.hoteldog.user.models.UserUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    Integer userSignup(UserSignupDto dto);
    Integer insUserAddress(UserAddressInfo entity);
    /*int insUserAddress(UserSignupDto dto);*/
    UserInfo userEntityByUserEmail(String userEmail);
    List<UserInfo> selUserEntity();
    List<Integer> selUserDogSize(int userPk);
    String selUserDepthName(int userPk);
    UserInfo userEntityByUserPk(int userPk);
    Integer updateUserInfo(UserUpdateDto dto);

    UserAddressInfo getUserAddress(int userPk);

    int updateUserAddress(UserAddressInfo entity);
}
