package com.green.hoteldog.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class HotelReservationInsDto {
    private int hotelPk;
    @JsonIgnore
    private int userPk;
    @JsonIgnore
    private Integer resPk;
    private String fromDate;
    private String toDate;
    private List<DogInfo> dogInfo;
}
