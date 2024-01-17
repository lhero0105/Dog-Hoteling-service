package com.green.hoteldog.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
//영웅
@Data
public class HotelReservationInsDto{
    private int hotelPk;
    @JsonIgnore
    private int userPk;
    private int hotelRoomPk;
    private String fromDate;
    private String toDate;
    private List<DogInfo> dogInfo;
    private int dogEa;
    @JsonIgnore
    private Integer resPk;
}
