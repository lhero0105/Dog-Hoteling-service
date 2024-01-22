package com.green.hoteldog.hotel.model;

import lombok.Data;

import java.util.List;

@Data
public class HotelRoomEaByDate {
    private String date;
    private List<HotelRoomEa> roomEas;
}
//승준
