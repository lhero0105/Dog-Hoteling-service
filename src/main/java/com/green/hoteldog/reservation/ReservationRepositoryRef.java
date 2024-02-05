package com.green.hoteldog.reservation;

import com.green.hoteldog.reservation.model.*;

import java.util.List;

public interface ReservationRepositoryRef {
    Integer insHotelReservation(HotelReservationInsDto dto);
    Integer insHotelReservationDogInfo(DogInfo info);
    Integer insHotelReservationInfo(HotelReservationInsDto dto);
    Integer updRemainedHotelRoom(List<HotelReservationUpdProcDto> dto);
    List<HotelReservationSelProcVo> selHotelReservation(HotelReservationDelDto dto);
    List<Integer> selHotelRoomPk(HotelReservationDelDto dto);
    Integer delHotelReservationInfo(HotelReservationDelDto dto);
    Integer delHotelReservationDogInfo(HotelReservationDelDto dto);
    Integer delHotelReservation(HotelReservationDelDto dto);
    Integer updRemainedHotelRoom2(HotelReservationUpdProc2Dto dto);
    List<ResInfoVo> getUserReservation(int userPk,int perPage,int pages);
    List<ResDogInfoVo> getDogInfoReservation(List<Integer> resPkList);
    List<ResHotelPicVo> getHotelResPic(List<Integer> hotelPk);
    List<Integer> getHotelPk(int userPk);


}
