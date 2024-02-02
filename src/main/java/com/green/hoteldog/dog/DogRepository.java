package com.green.hoteldog.dog;

import com.green.hoteldog.dog.models.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class DogRepository implements DogRepositoryRef{
    private final DogMapper dogMapper;
    public Integer insUserDog(InsUserDogDto dto) {
        return dogMapper.insUserDog(dto);
    }
    public Integer updUserDog(UpdUserDogDto dto) {
        return dogMapper.updUserDog(dto);
    }
    public Integer delUserDog(DelUserDogDto dto) {
        return dogMapper.delUserDog(dto);
    }
    public List<GetDogListVo> selUserDog(GetUserDogDto dto) {
        return dogMapper.selUserDog(dto);
    }
    public Integer setUserDogPic(SetUserDogPicDto dto) {
        return dogMapper.setUserDogPic(dto);
    }
}
