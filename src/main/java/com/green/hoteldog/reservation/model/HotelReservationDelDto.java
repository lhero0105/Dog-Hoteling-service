package com.green.hoteldog.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class HotelReservationDelDto {
    @Size(min = 1)
    @JsonIgnore
    @JsonProperty(value = "user_pk")
    private int userPk;
    @Size(min = 1)
    @JsonProperty(value = "hotel_pk")
    private int hotelPk;
    @Size(min = 1)
    @JsonProperty(value = "hotel_room_pk")
    private int hotelRoomPk;
    @JsonIgnore
    private int resPk;
}