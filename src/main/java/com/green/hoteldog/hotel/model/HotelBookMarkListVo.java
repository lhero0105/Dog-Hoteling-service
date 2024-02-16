package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "북마크 등록 한 호텔 리스트")
public class HotelBookMarkListVo {
    @JsonProperty(value = "avg_star")
    private float avgStar;
    @JsonProperty(value = "hotelRoomCost")
    private int hotelRoomCost;
    @JsonProperty(value = "hotel_pk")
    private int hotelPk;
    @JsonProperty(value = "hotel_nm")
    private String hotelNm;
    @JsonProperty(value = "address_name")
    private String addressName;
    @JsonProperty(value = "hotel_pic")
    private String hotelPic;
    @JsonProperty(value = "discount_per")
    private String discountPer;
    @JsonProperty(value = "book_mark")
    private int bookMark;
    @JsonProperty(value = "review_count")
    private int reviewCount;
}
