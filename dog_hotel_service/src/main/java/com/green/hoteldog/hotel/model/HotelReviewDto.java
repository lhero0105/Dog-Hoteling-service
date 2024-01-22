package com.green.hoteldog.hotel.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HotelReviewDto {
    private int reviewPk;
    private int userPk;
    private int hotelPk;
}
//승준