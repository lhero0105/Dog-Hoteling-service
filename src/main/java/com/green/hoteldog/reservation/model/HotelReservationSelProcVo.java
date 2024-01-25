package com.green.hoteldog.reservation.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelReservationSelProcVo {
    private Integer resPk;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int roomCount;
    private int resDogPk;
}
