package com.green.hoteldog.reservation;

import com.green.hoteldog.reservation.model.HotelReservationDelDto;
import com.green.hoteldog.reservation.model.HotelReservationInsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    // 호텔 예약
    Integer insHotelReservation(HotelReservationInsDto dto);
    int insHotelReservationDogMineInfo(HotelReservationInsDto dto);
    int insHotelReservationDogWriteInfo(HotelReservationInsDto dto);

    // 예약 취소
    Integer selHotelReservation(HotelReservationDelDto dto);
    int delHotelReservationDogMineInfo(HotelReservationDelDto dto);
    int delHotelReservationDogWriteInfo(HotelReservationDelDto dto);
    int delHotelReservation(HotelReservationDelDto dto);
    //영웅
}