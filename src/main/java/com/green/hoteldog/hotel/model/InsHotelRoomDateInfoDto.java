package com.green.hoteldog.hotel.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class InsHotelRoomDateInfoDto {
    private int hotelRoomPk;
    private int hotelLeftEa;
    private List<LocalDate> roomDate;
}
