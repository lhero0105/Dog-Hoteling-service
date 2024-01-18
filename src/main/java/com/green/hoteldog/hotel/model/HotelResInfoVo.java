package com.green.hoteldog.hotel.model;

import lombok.Data;

@Data
public class HotelResInfoVo {
    private int resPk;
    private int userPk;
    private String fromDate;
    private String toDate;

    private int resStatus;
}
//승준