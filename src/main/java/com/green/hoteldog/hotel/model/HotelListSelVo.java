package com.green.hoteldog.hotel.model;

import lombok.Data;

@Data
public class HotelListSelVo {
    private int hotelPk;
    private String hotelNm;
    private String addressName;
    private String hotelPic; // 가장 처음 등록한 사진
    private float star; // 리뷰 평점 평균
    private String price; // 하루 단위 최저가 방 가격
    private int discountPer;
    private int bookMark;
}
