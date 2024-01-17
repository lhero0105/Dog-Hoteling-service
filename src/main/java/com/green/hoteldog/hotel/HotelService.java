package com.green.hoteldog.hotel;

import com.green.hoteldog.hotel.model.HotelListSelDto;
import com.green.hoteldog.hotel.model.HotelListSelProcDto;
import com.green.hoteldog.hotel.model.HotelListSelVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelMapper mapper;

    List<HotelListSelVo> getHotelList(HotelListSelDto dto){
        // 1. 등록된 반려견 정보와 주소로 사용자화
        // 1-1. 비회원 첫화면 - 최신순
        if(dto.getUserPk() == 0 && dto.getAddressPk() == 0 && dto.getFromDate() == null
            && dto.getToDate() == null && dto.getDogCount() == 0 && dto.getSearch() == null
                && dto.getSwimmingPool() == 0 && dto.getPlayGround() == 0 && dto.getHandMadeFood() == 0
                && dto.getPickUp() == 0 && dto.getDogBeauty() == 0 && dto.getDogProgram() == 0
                && dto.getDogWalking() == 0){
            return mapper.selHotelListToNonMember(dto);
        // 1-2. 회원 첫화면 - 주소, 반려견 강아지 사이즈
        } else if (dto.getUserPk() > 0 && dto.getAddressPk() == 0 && dto.getFromDate() == null
                && dto.getToDate() == null && dto.getDogCount() == 0 && dto.getSearch() == null
                && dto.getSwimmingPool() == 0 && dto.getPlayGround() == 0 && dto.getHandMadeFood() == 0
                && dto.getPickUp() == 0 && dto.getDogBeauty() == 0 && dto.getDogProgram() == 0
                && dto.getDogWalking() == 0){
            // 1-2-1. 회원 pk로 강아지 사이즈, 주소 pk 셀렉
            HotelListSelProcDto pDto = mapper.selUserInfoToUserPk(dto);
            return mapper.selHotelListToMember(pDto);
        }
        // 2. 호텔 이름 검색 시 리스트
        if(dto.getSearch() != null && dto.getAddressPk() == 0 && dto.getFromDate() == null
                && dto.getToDate() == null && dto.getDogCount() == 0 && dto.getDogWalking() == 0
                && dto.getSwimmingPool() == 0 && dto.getPlayGround() == 0 && dto.getHandMadeFood() == 0
                && dto.getPickUp() == 0 && dto.getDogBeauty() == 0 && dto.getDogProgram() == 0){
            return mapper.selHotelListToSearch(dto);
        }
        // 3. 필터링 시 리스트
        if((dto.getAddressPk() > 0 || dto.getFromDate() != null || dto.getToDate() != null
                || dto.getDogCount() > 0 || dto.getSwimmingPool() > 0 || dto.getPlayGround() > 0
                || dto.getHandMadeFood() > 0 || dto.getPickUp() > 0 || dto.getDogBeauty() > 0
                || dto.getDogProgram() > 0 || dto.getDogWalking() > 0) && dto.getSearch() == null){
            return mapper.selHotelListToFilter(dto);
        }
        return null;
    }
    //영웅
}
