package com.green.hoteldog.dog;

import com.green.hoteldog.dog.models.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DogMapper {
    Integer insUserDog (InsUserDogDto dto);
    Integer updUserDog (UpdUserDogDto dto);
    Integer delUserDog (DelUserDogDto dto);
    List<GetDogListVo> selUserDog (GetUserDogDto dto);
    Integer setUserDogPic(SetUserDogPicDto dto);
}
