package com.green.hoteldog.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
//영웅
@Data
public class HotelReservationInsDto{
    @JsonProperty(value = "hotel_pk")
    private int hotelPk;
    @JsonProperty(value = "from_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;
    @JsonProperty(value = "to_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate toDate;
    @JsonProperty(value = "dog_info")
    private List<DogInfo> dogInfo;
    @JsonIgnore
    private int userPk;
    @JsonIgnore
    private Integer resPk;
}
