package com.green.hoteldog.hotel.model;

import lombok.Data;

import java.util.List;

@Data
public class HotelRoomResInfoWithDogs {
    private int hotelPk;
    private String startDate;
    private String endDate;
    private List<Integer> dogs;
}
