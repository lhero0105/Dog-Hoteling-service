package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class HotelInsDto {
    @JsonIgnore
    private int hotelPk;
    private int userPk;
    private String hotelNm;
    private String hotelDetailInfo;
    private String businessNum;
    private String hotelCall;
    private List<Integer> hotelOption;
    private HotelAddressDto addressDto;
}
