package com.green.hoteldog.hotel.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class HotelRoomAbleListDto {
    int hotelPk;
    String startDate;
    String endDate;
    List<Integer> dogPks;
}
//승준
