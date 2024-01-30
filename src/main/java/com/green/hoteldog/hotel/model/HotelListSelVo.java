package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotelListSelVo {
    @JsonProperty(value = "hotel_pk")
    private int hotelPk;
    @JsonProperty(value = "hotel_nm")
    private String hotelNm;
    @JsonProperty(value = "address_name")
    private String addressName;
    @JsonProperty(value = "hotel_pic")
    private String hotelPic; // 가장 처음 등록한 사진
    private float star; // 리뷰 평점 평균
    private String price; // 하루 단위 최저가 방 가격
    @JsonProperty(value = "discount_per")
    private int discountPer;
    @JsonProperty(value = "book_mark")
    private int bookMark;
}
