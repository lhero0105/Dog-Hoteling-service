package com.green.hoteldog.review.models;

import lombok.Data;

@Data
public class UserResRoomVo {
    private int reviewPk;
    private int resPk;
    private int hotelRoomPk;
    private String hotelRoomNm;
}
