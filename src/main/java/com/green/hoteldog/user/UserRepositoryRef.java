package com.green.hoteldog.user;

import com.green.hoteldog.user.models.UserAddressEntity;
import com.green.hoteldog.user.models.UserEntity;
import com.green.hoteldog.user.models.UserSignupDto;
import com.green.hoteldog.user.models.UserUpdateDto;

import java.util.List;

public interface UserRepositoryRef {
    Integer userSignup(UserSignupDto dto);
    Integer insUserAddress(UserAddressEntity entity);
    /*int insUserAddress(UserSignupDto dto);*/
    UserEntity userEntityByUserEmail(String userEmail);
    List<UserEntity> selUserEntity();
    List<Integer> selUserDogSize(int userPk);
    String selUserDepthName(int userPk);
    UserEntity userEntityByUserPk(int userPk);
    Integer updateUserInfo(UserUpdateDto dto);
}
