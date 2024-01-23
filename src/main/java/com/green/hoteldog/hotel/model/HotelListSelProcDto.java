package com.green.hoteldog.hotel.model;

import lombok.Data;

import java.util.List;

@Data
public class HotelListSelProcDto {
    // 회원 첫화면 시 사이즈 pk, 주소 저장
    private List<Integer> dogSizePks;
    private String address;
}
