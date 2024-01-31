package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HotelListSelAllVo {
    @JsonProperty(value = "hotel_advertise_list")
    List<HotelListSelVo> hotelAdvertiseList = new ArrayList<>();
    @JsonProperty(value = "hotel_list")
    List<HotelListSelVo> hotelList = new ArrayList<>();
}
