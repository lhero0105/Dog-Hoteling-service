package com.green.hoteldog.user;

import com.green.hoteldog.user.models.UserAddressEntity;
import com.green.hoteldog.user.models.UserEntity;
import com.green.hoteldog.user.models.UserSignupDto;
import com.green.hoteldog.user.models.UserUpdateDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    Integer userSignup(UserSignupDto dto);
    Integer insUserAddress(UserAddressEntity entity);
    /*int insUserAddress(UserSignupDto dto);*/
    UserEntity userEntityByUserEmail(String userEmail);
    List<UserEntity> selUserEntity();
    List<Integer> selUserDogSize(int userPk);
    String selUserDepthName(int userPk);
    UserEntity userEntityByUserPk(int userPk);
    Integer updateUserInfo(UserUpdateDto dto);

    UserAddressEntity getUserAddress(int userPk);
}
