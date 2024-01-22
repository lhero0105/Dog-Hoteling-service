package com.green.hoteldog.dog;

import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.dog.models.DelUserDogDto;
import com.green.hoteldog.dog.models.GetDogListVo;
import com.green.hoteldog.dog.models.InsUserDogDto;
import com.green.hoteldog.dog.models.UpdUserDogDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dog")
public class DogController {
    private final DogService service;

    //유저가 등록한 강아지 리스트 호출
    @GetMapping
    public List<GetDogListVo> selUserDogList(){
        return service.selUserDogList();
    }
    //유저 강아지 등록
    @PostMapping
    public ResVo postUserDog(@RequestPart MultipartFile pic,
                             @RequestBody InsUserDogDto dto){
        dto.setDogPic(pic);
        return service.insUserDog(dto);
    }
    //유저 강아지 정보 수정
    @PutMapping
    public ResVo putUserDog(@RequestBody UpdUserDogDto dto){
        return service.updUserDog(dto);
    }
    //유저 강아지 사진 수정
    @PatchMapping
    public ResVo patchUserDogPic(@RequestPart MultipartFile pic
                                 ,@RequestBody int userDogPk){
        return service.updUserDogPic(pic,userDogPk);
    }
    //유저 강아지 삭제
    @DeleteMapping
    public ResVo delUserDog(DelUserDogDto dto){
        return service.delUserDog(dto);
    }

}