package com.green.hoteldog.hotel.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class HotelRoomAbleListDto {
    private int hotelPk;
    private String startDate;
    private String endDate;

    private List<Integer> dogPks;
}
//승준
