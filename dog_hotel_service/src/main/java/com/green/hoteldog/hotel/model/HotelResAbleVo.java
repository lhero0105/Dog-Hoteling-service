package com.green.hoteldog.hotel.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "호텔 예약 가능여부," +
        "<br>예약되어있는 강아지 마릿수 및 날짜")
public class HotelResAbleVo {
    @Schema(name = "날짜")
    private String date;

    private int resAble=0;
    @Schema(name = "예약가능한 강아지 마릿수") // 추후 수정요망.
    private int dogCnt;
}
//승준