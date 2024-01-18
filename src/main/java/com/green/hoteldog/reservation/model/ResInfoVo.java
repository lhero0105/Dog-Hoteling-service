package com.green.hoteldog.reservation.model;

import lombok.Data;

import java.util.List;

@Data
public class ResInfoVo {

    private String hotelNm;
    private String hotelCall;
    private String hotelRoomNm;
    private String fromDate;
    private String toDate;
    private String roomPic;
    private int resPk;
    private List<ResDogInfoVo> resDogInfoVoList;
}
//승준