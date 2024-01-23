package com.green.hoteldog.reservation;

import com.green.hoteldog.reservation.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    //-------------------------------------------------호텔 예약----------------------------------------------------------
    Integer insHotelReservation(HotelReservationInsDto dto);
    int insHotelReservationDogMineInfo(HotelReservationInsDto dto);
    int insHotelReservationDogWriteInfo(HotelReservationInsDto dto);

    //-------------------------------------------------예약 취소----------------------------------------------------------
    Integer selHotelReservation(HotelReservationDelDto dto);
    int delHotelReservationDogMineInfo(HotelReservationDelDto dto);
    int delHotelReservationDogWriteInfo(HotelReservationDelDto dto);
    int delHotelReservation(HotelReservationDelDto dto);
    //영웅

    //--------------------------------------------예약 정보 불러오기--------------------------------------------------------
    List<ResInfoVo> getUserReservation(ResInfoDto dto);
    List<ResDogInfoVo> getDogInfoReservation(List<Integer> resPk);
    //승준
}