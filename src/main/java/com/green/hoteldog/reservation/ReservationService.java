package com.green.hoteldog.reservation;

import com.green.hoteldog.common.Const;
import com.green.hoteldog.common.ResVo;
import com.green.hoteldog.reservation.model.*;
import com.green.hoteldog.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private final AuthenticationFacade authenticationFacade;
    //--------------------------------------------------호텔 예약---------------------------------------------------------
    public ResVo postHotelReservation(List<HotelReservationInsDto> dto){
        log.info("dto : {}", dto);
        for ( HotelReservationInsDto dtos : dto ) {
            int respk = mapper.insHotelReservation(dtos);
            dtos.setResPk(respk);
            if (dtos.getResPk() == null){
                return new ResVo(Const.FAIL); // 예약 테이블에 등록 실패
            }
            int affectedRows1 = mapper.insHotelReservationDogInfo(dtos);
            if(affectedRows1 == 0){
                return new ResVo(Const.FAIL); // 예약 강아지 정보 등록 실패
            }
        }
        int affectedRows2 = mapper.insHotelReservationInfo(dto);
        if(affectedRows2 == 0){
            return new ResVo(Const.FAIL); // 예약방강아지 예약정보 등록 실패
        }
        // 호텔 방 관리 테이블 업데이트
        // 날짜 list , 호텔 방 pk list 박스 생성
        List<HotelReservationUpdProcDto> updList = new ArrayList<>();
        for (HotelReservationInsDto hotelReservationInsDto : dto) {
            for (DogInfo dogInfo : hotelReservationInsDto.getDogInfo()) {
                HotelReservationUpdProcDto updProcDto = new HotelReservationUpdProcDto();
                updProcDto.setHotelRoomPk(dogInfo.getHotelRoomPk());

                LocalDate fromDate = hotelReservationInsDto.getFromDate();
                LocalDate toDate = hotelReservationInsDto.getToDate();
                // fromDate부터 toDate까지 날짜 배열 생성
                List<LocalDate> dateRange = new ArrayList<>();
                while (!fromDate.isAfter(toDate)) {
                    dateRange.add(fromDate);
                    fromDate = fromDate.plusDays(1);
                }
                /*// 날짜 출력
                for ( LocalDate dateList : dateRange ) {
                    log.info("date : {}", dateList);
                }*/
                updProcDto.setDate(dateRange);
                updList.add(updProcDto);
            }
        }

        int affectedRows3 = mapper.updRemainedHotelRoom(updList);
        if(affectedRows3 == 0){
            return new ResVo(Const.FAIL);
        }
        return new ResVo(Const.SUCCESS);
    }


    //---------------------------------------------------예약 취소--------------------------------------------------------
    public ResVo delHotelReservation(HotelReservationDelDto dto){
        // 먼저 유저가 예약 했는지 셀렉트
        List<HotelReservationSelProcVo> procVo = mapper.selHotelReservation(dto);
        if(procVo.size() == 0){
            return new ResVo(Const.FAIL); // 예약 정보 없음
        }
        for ( HotelReservationSelProcVo vo: procVo ) {
            dto.setResPk(vo.getResPk()); // 다 같은 pk
            dto.getResDogPkList().add(vo.getResDogPk());
        }
        dto.setHotelRoomPk(mapper.selHotelRoomPk(dto));

        // 호텔방강아지테이블 삭제
        int affectedRows1 = mapper.delHotelReservationInfo(dto);
        log.info("affectedRowsHotelReservationInfoDel : {}", affectedRows1);
        if(affectedRows1 == 0){
            return new ResVo(Const.FAIL);
        }
        // 예약 강아지 테이블 삭제
        int affectedRows2 = mapper.delHotelReservationDogInfo(dto);
        log.info("affectedRowsHotelDogInfoDel : {}", affectedRows2);
        if(affectedRows2 == 0){
            return new ResVo(Const.FAIL);
        }
        // 호텔 예약 테이블 삭제
        int affectedRows3 = mapper.delHotelReservation(dto);
        log.info("affectedRowsHotelReservationDel : {}", affectedRows3);
        if(affectedRows3 == 0){
            return new ResVo(Const.FAIL);
        }
        // 호텔방 관리테이블 업데이트
        // fromdate, todate를 다 가져와 dateRange로 변경
        List<LocalDate> dateRange = new ArrayList<>();
        for ( HotelReservationSelProcVo vo: procVo ) {
            LocalDate fromDate = vo.getFromDate();
            LocalDate toDate = vo.getToDate();
            while (!fromDate.isAfter(toDate)) {
                dateRange.add(fromDate);
                fromDate = fromDate.plusDays(1);
            } // 여러번 돌아도 같은값
            dto.setDateRange(dateRange);
        }

        HotelReservationUpdProc2Dto pDtoList = new HotelReservationUpdProc2Dto();
        pDtoList.setDate(dateRange);
        pDtoList.setHotelRoomPk(dto.getHotelRoomPk());
        int affectedRows4 = mapper.updRemainedHotelRoom2(pDtoList);
        log.info("affectedRowsHotelRoomUpd : {}", affectedRows4);
        if(affectedRows4 == 0){
            return new ResVo(Const.FAIL);
        }
        return new ResVo(Const.SUCCESS);
    }
    //영웅

    //--------------------------------------------------예약 정보---------------------------------------------------------
    public List<ResInfoVo> getUserReservation(ResInfoDto dto){
        dto.setUserPk(authenticationFacade.getLoginUserPk());
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
