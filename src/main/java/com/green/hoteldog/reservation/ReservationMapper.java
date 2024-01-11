package com.green.hoteldog.reservation;

import com.green.hoteldog.reservation.model.HotelReservationInsDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationMapper {
    Integer insHotelReservation(HotelReservationInsDto dto);
    int insHotelReservationDogInfo(HotelReservationInsDto dto);
}
