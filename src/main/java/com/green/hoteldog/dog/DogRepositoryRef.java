package com.green.hoteldog.dog;

import com.green.hoteldog.dog.models.*;

import java.util.List;

public interface DogRepositoryRef {
    Integer insUserDog (InsUserDogDto dto);
    Integer updUserDog (UpdUserDogDto dto);
    Integer delUserDog (DelUserDogDto dto);
    List<GetDogListVo> selUserDog (GetUserDogDto dto);
    Integer setUserDogPic(SetUserDogPicDto dto);
}
