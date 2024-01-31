package com.green.hoteldog.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
public class HotelReservationInsDto{
    @Min(0)
    @JsonProperty(value = "hotel_pk")
    private int hotelPk;
    @JsonProperty(value = "from_date")
    private LocalDate fromDate;
    @JsonProperty(value = "to_date")
    private LocalDate toDate;
    @JsonProperty(value = "dog_info")
    private List<DogInfo> dogInfo;
    @JsonIgnore
    private int userPk;
    @JsonIgnore
    private Integer resPk;
}
