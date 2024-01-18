package com.green.hoteldog.hotel.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "호텔 리뷰의 사진")
public class HotelReviewPicVo {
    private List<String> pics;
}
//승준