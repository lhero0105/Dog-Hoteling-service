package com.green.hoteldog.user.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserHotelFavDto {
    @JsonProperty(value = "user_pk")
    private int userPk;
    @JsonProperty(value = "hotel_pk")
    private int hotelPk;
}
