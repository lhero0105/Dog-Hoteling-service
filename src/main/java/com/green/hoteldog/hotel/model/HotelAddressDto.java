package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class HotelAddressDto {
    @JsonIgnore
    private int hotelPk;
    private String addressName;
    private String region1DepthName;
    private String region2DepthName;
    private String region3DepthName;
    private String zoneNum;
    private String x;
    private String y;
    private String detailAddress;
}
