package com.green.hoteldog.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//영웅
@Data
public class HotelReservationDelDto {
    private int resPk;
    @JsonIgnore
    private int userPk;
    @JsonIgnore
    private List<Integer> resDogPkList = new ArrayList<>();
    @JsonIgnore
    private List<LocalDate> dateRange;
    @JsonIgnore
    private List<Integer> hotelRoomPk;
}