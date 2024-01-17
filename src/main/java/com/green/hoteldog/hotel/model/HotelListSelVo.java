package com.green.hoteldog.hotel.model;

import lombok.Builder;
import lombok.Getter;
//영웅
@Builder
@Getter
public class HotelListSelVo {
    private int hotelPk;
    private String hotelNm;
    private String address;
    private float star; // 리뷰 평점 평균
    private String price; // 하루단위 이며, 1박 시 두배
    // 가장 가격이 낮은 방의 가격 -> 방 등록 시 pk 1에 등록
}
