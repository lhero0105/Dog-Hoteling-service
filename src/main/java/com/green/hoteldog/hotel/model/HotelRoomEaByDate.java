package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "날짜별로 가능한 방 리스트")
public class HotelRoomEaByDate {
    private String date;
    @JsonProperty(value = "room_eas")
    private List<HotelRoomEa> roomEas;
}

