package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "호텔 방 이름과 그 방의 남은 방 갯수")
public class HotelRoomEa{
    @NotNull
    @JsonProperty(value = "hotel_room_nm")
    private String hotelRoomNm;

    @NotNull
    @JsonProperty(value = "room_left_ea")
    private int roomLeftEa;
}

