package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class HotelListSelDto {
    // @JsonProperty(required = false) 기본
    @JsonIgnore
    private int userPk;
    @JsonProperty(value = "address_pk")
    private int addressPk;
    @JsonProperty(value = "from_date")
    private String fromDate;
    @JsonProperty(value = "to_date")
    private String toDate;
    @JsonProperty(value = "dog_count")
    private int dogCount;
    private String search; // 호텔이름 검색
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int rowCount;
    // 호텔 이용 테마
    @JsonProperty(value = "swimming_pool")
    private int swimmingPool;
    @JsonProperty(value = "play_ground")
    private int playGround;
    @JsonProperty(value = "hand_made_food")
    private int handMadeFood;
    @JsonProperty(value = "pick_up")
    private int pickUp;
    @JsonProperty(value = "dog_beauty")
    private int dogBeauty;
    @JsonProperty(value = "dog_program")
    private int dogProgram;
    @JsonProperty(value = "dog_walking")
    private int dogWalking;

    public void setPage(int page){
        startIdx = (page - 1) * rowCount;
    }
}
