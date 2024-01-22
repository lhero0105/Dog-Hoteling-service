package com.green.hoteldog.hotel.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HotelListSelAllVo {
    List<HotelListSelVo> hotelAdvertiseList = new ArrayList<>();
    List<HotelListSelVo> hotelList = new ArrayList<>();
}
