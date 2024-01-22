package com.green.hoteldog.dog;

import com.green.hoteldog.common.MyFileUtils;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.dog.models.*;
import com.green.hoteldog.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogService {
    private final DogMapper mapper;
    private final MyFileUtils fileUtils;
    private final AuthenticationFacade facade;

    //유저가 등록한 강아지 리스트 호출
    public List<GetDogListVo> selUserDogList(){
        return mapper.selUserDog(facade.getLoginUserPk());
    }

    //유저 강아지 등록
    public ResVo insUserDog(InsUserDogDto dto){
        dto.setUserPk(facade.getLoginUserPk());
        String target = "/user/"+dto.getUserPk()+"/"+dto.getUserDogPk();
        String saveFileNm = fileUtils.transferTo(dto.getDogPic(),target);
        SetUserDogPicDto picDto = new SetUserDogPicDto();
        picDto.setPic(saveFileNm);
        picDto.setUserDogPk(dto.getUserDogPk());
        picDto.setUserPk(dto.getUserPk());
        int result = mapper.insUserDog(dto);
        return new ResVo(result);
    }

    //유저 강아지 정보 수정
    public ResVo updUserDog(UpdUserDogDto dto){
        dto.setUserPk(facade.getLoginUserPk());
        int result = mapper.updUserDog(dto);
        return new ResVo(result);
    }
    public ResVo updUserDogPic(MultipartFile pic,int userDogPk){
        String target = "/user/"+facade.getLoginUserPk()+"/"+userDogPk;
        fileUtils.delFolderTrigger(target);
        String saveFileNm = fileUtils.transferTo(pic,target);
        SetUserDogPicDto picDto = new SetUserDogPicDto();
        picDto.setUserPk(facade.getLoginUserPk());
        picDto.setUserDogPk(userDogPk);
        picDto.setPic(saveFileNm);
        try {
            mapper.setUserDogPic(picDto);
        }catch (Exception e){
            return new ResVo(0);
        }
        return new ResVo(1);


    }

    //유저 강아지 삭제
    public ResVo delUserDog(DelUserDogDto dto){
        dto.setUserPk(facade.getLoginUserPk());
        String target = "/user/" + dto.getUserPk();
        try {
            mapper.delUserDog(dto);
        }catch (Exception e){
            return new ResVo(0);
        }
        fileUtils.delFolderTrigger(target);
        return new ResVo(1);
    }
}
