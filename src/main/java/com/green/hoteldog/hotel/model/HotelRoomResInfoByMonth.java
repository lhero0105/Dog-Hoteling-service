package com.green.hoteldog.hotel.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HotelRoomResInfoByMonth {
    @NotNull
    @JsonProperty(value = "hotel_room_nm")
    private String hotelRoomNm;

    @NotNull
    @JsonProperty(value = "room_left_ea")
    private int roomLeftEa;

    @NotNull
    @JsonProperty(value = "room_date")
    private String roomDate;

}
//승준
