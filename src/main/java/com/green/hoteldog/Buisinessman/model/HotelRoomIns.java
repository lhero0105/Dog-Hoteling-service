package com.green.hoteldog.Buisinessman.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelRoomIns {
    @JsonProperty("hotel_pk")
    private int hotelPk;
    @JsonProperty("size_pk")
    private int sizePk;
    @JsonProperty("hotel_room_nm")
    private String hotelRoomNm;
    @JsonProperty("room_pic")
    private String roomPic;
    @JsonProperty("hotel_room_ea")
    private int hotelRoomEa;
    @JsonProperty("hotel_room_cost")
    private int hotelRoomCost;
    private int maximum;
    @JsonProperty("book_able")
    private int bookAble;
}
