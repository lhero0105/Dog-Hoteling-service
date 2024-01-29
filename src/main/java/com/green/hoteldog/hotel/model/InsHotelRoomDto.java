package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class InsHotelRoomDto {
    @JsonIgnore
    private int hotelRoomPk;
    @JsonIgnore
    private int userPk;
    private int hotelPk;
    private int sizePk;
    private String hotelRoomNm;
    private String roomPic;
    private int hotelRoomEa;
    private int hotelRoomCost;
    private int maximum;
}
