package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HotelRoomResInfo {
    @NotNull
    @JsonProperty(value = "hotel_room_nm")
    private String hotelRoomNm;

    @NotNull
    @JsonProperty(value = "hotel_room_left")
    private int hotelRoomLeft;

    private String roomDate;
}
//승준
