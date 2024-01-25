package com.green.hoteldog.reservation.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class HotelReservationUpdProcDto {
    private int hotelRoomPk;
    private List<LocalDate> date;
}
