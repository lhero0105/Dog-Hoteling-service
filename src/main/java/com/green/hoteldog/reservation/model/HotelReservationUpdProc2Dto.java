package com.green.hoteldog.reservation.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HotelReservationUpdProc2Dto {
    private List<Integer> hotelRoomPk;
    private List<LocalDate> date; // 날짜는 다 동일
}
