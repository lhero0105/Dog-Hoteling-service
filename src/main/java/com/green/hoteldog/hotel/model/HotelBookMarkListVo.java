package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelBookMarkListVo {
    @JsonProperty(value = "hotel_nm")
    private String hotelNm;
    @JsonProperty(value = "hotel_pic")
    private String hotelPic;
    @JsonProperty(value = "hotel_pk")
    private int hotelPk;
}
