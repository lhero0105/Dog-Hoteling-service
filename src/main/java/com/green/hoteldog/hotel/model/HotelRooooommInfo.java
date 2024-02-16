package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelRooooommInfo {
    private String pic;
    @JsonProperty(value = "hotel_room_nm")
    private String hotelRoomNm;
    @JsonProperty(value = "room_left_ea")
    private int roomLeftEa;
    @JsonProperty(value = "hotel_room_cost")
    private int hotelRoomCost;
}
