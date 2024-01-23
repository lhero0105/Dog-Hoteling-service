package com.green.hoteldog.hotel.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema()
public class HotelInfoEntity {
    @Schema(name = "호텔 정보 + 리뷰")
    private HotelInfoVo hotelInfoVo;

    private List<MyDog> myDogList;

    private List<HotelRoomEaByDate> roomEaByDates;

}
//승준