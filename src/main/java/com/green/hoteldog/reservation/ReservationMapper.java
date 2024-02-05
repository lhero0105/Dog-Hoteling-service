package com.green.hoteldog.reservation;

import com.green.hoteldog.reservation.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    //-------------------------------------------------호텔 예약----------------------------------------------------------
    Integer insHotelReservation(HotelReservationInsDto dto);
    Integer insHotelReservationDogInfo(DogInfo info);
    Integer insHotelReservationInfo(HotelReservationInsDto dto);
    Integer updRemainedHotelRoom(List<HotelReservationUpdProcDto> dto);
    //-------------------------------------------------예약 취소----------------------------------------------------------
    List<HotelReservationSelProcVo> selHotelReservation(HotelReservationDelDto dto);
    List<Integer> selHotelRoomPk(HotelReservationDelDto dto);
    Integer delHotelReservationInfo(HotelReservationDelDto dto);
    Integer delHotelReservationDogInfo(HotelReservationDelDto dto);
    Integer delHotelReservation(HotelReservationDelDto dto);
    Integer updRemainedHotelRoom2(HotelReservationUpdProc2Dto dto);
    //--------------------------------------------예약 정보 불러오기--------------------------------------------------------
    List<ResInfoVo> getUserReservation(int userPk,int perPage,int pages);
    List<ResDogInfoVo> getDogInfoReservation(List<Integer> resPkList);
    List<ResHotelPicVo> getHotelResPic(List<Integer> hotelPkList);
    List<Integer> getHotelPk(int userPk);
}