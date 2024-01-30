package com.green.hoteldog.reservation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ResInfoVo {
    @JsonProperty(value = "hotel_nm")
    private String hotelNm;
    @JsonProperty(value = "hotel_call")
    private String hotelCall;
    @JsonProperty(value = "hotel_room_nm")
    private String hotelRoomNm;
    @JsonProperty(value = "from_date")
    private String fromDate;
    @JsonProperty(value = "to_date")
    private String toDate;
    @JsonProperty(value = "room_pic")
    private String roomPic;
    @JsonProperty(value = "res_pk")
    private int resPk;
    @JsonProperty(value = "res_dog_info_vo_list")
    private List<ResDogInfoVo> resDogInfoVoList;
}
