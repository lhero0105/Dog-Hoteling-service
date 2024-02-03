package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "북마크 등록 한 호텔 리스트")
public class HotelBookMarkListVo {
    @Schema(name = "호텔 이름")
    @JsonProperty(value = "hotel_nm")
    private String hotelNm;
    @Schema(name = "호텔 사진")
    @JsonProperty(value = "hotel_pic")
    private String hotelPic;
    @Schema(name = "호텔Pk")
    @JsonProperty(value = "hotel_pk")
    private int hotelPk;
    @Schema(name = "호텔 최저가")
    @JsonProperty(value = "hotel_room_cost")
    private int hotelRoomCost;
    @Schema(name = "평균별점")
    @JsonProperty(value = "avg_star")
    private float avgStar;
    @Schema(name = "할인율")
    @JsonProperty(value = "discount_per")
    private String discountPer;
    @Schema(name = "리뷰수")
    @JsonProperty(value = "review_count")
    private int reviewCount;
    @Schema(name = "주소")
    @JsonProperty(value = "address_name")
    private String addressName;
    @Schema(name = "북마크")
    @JsonProperty(value = "book_mark")
    private int bookMark;
}
