package com.green.hoteldog.reservation;

import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.reservation.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationMapper mapper;

    // 호텔 예약
    public ResVo postHotelReservation(List<HotelReservationInsDto> dto){
        log.info("dto : {}", dto);
        for ( HotelReservationInsDto dtos : dto ) {
            Integer respk = mapper.insHotelReservation(dtos);
            dtos.setResPk(respk);
            if (dtos.getResPk() == null){
                return new ResVo(Const.FAIL); // 예약 테이블에 등록 실패
            }
            for ( DogInfo info : dtos.getDogInfo() ) {
                if(info.getUserDogInfo() > 0){
                    // 등록된 강아지 인지 체크
                   int affectedRows =  mapper.insHotelReservationDogMineInfo(dtos);
                   log.info("affectedRowsOfDogMineInfo : {}", affectedRows);
                   if(affectedRows == 0){
                       return new ResVo(Const.FAIL); // 등록된 강아지 테이블에 등록 실패
                   }
                } else {
                    // 직접 입력된 강아지 인서트
                    int affectedRows =  mapper.insHotelReservationDogWriteInfo(dtos);
                    log.info("affectedRowsOfDogWriteInfo : {}", affectedRows);
                    if(affectedRows == 0){
                        return new ResVo(Const.FAIL); // 등록한 강아지 테이블에 등록 실패
                    }
                }
            }
        }
        return new ResVo(Const.SUCCESS);
    }

    // 예약 취소
    public ResVo delHotelReservation(HotelReservationDelDto dto){
        // 먼저 유저가 예약 했는지 셀렉트
        Integer resPk = mapper.selHotelReservation(dto);
        dto.setResPk(resPk);
        if(resPk == null){
            return new ResVo(Const.FAIL); // 예약 정보 없음
        }
        // 셀렉트 된게 있으면 삭제
        // 1. 등록된 강아지 테이블 삭제
        int affectedRows1 = mapper.delHotelReservationDogMineInfo(dto);
        log.info("affectedRowsOfDogMineInfo : {}", affectedRows1);

        // 2. 직접 입력한 강아지 테이블 삭제
        int affectedRows2 = mapper.delHotelReservationDogWriteInfo(dto);
        log.info("affectedRowsOfDogWriteInfo : {}", affectedRows2);

        // 3. 호텔 예약 테이블 삭제
        int affectedRows3 = mapper.delHotelReservation(dto);
        log.info("affectedRowsHotelReservation : {}", affectedRows3);
        if(affectedRows3 == 0){
            return new ResVo(Const.FAIL);
        }
        return new ResVo(Const.SUCCESS);
    }
    //영웅

    public List<ResInfoVo> getUserReservation(ResInfoDto dto){

        //예약정보
        List<ResInfoVo> resInfoVos = mapper.getUserReservation(dto);
        List<Integer> resPkList = resInfoVos
                .stream()
                .map(ResInfoVo::getResPk)
                .collect(Collectors.toList());
        List<ResDogInfoVo> resDogInfo = mapper.getDogInfoReservation(resPkList);

        resInfoVos.forEach(resInfoVo -> {
            List<ResDogInfoVo> resDogInfoVoList = resDogInfo
                    .stream()
                    .filter(resDogInfoVo -> resInfoVo.getResPk() == resDogInfoVo.getResPk())
                    .collect(Collectors.toList());
            resInfoVo.setResDogInfoVoList(resDogInfoVoList);
        });





        return resInfoVos;
    }
    //승준
}
