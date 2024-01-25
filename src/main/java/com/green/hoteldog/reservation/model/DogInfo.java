package com.green.hoteldog.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
//영웅
@Data
public class DogInfo {
    @JsonIgnore
    @JsonProperty(value = "res_dog_pk")
    private Integer resDogPk;
    @JsonProperty(value = "size_pk")
    private int sizePk;
    @JsonProperty(value = "dog_nm")
    private String dogNm;
    @JsonProperty(value = "dog_age")
    private int dogAge;
    private String information; // 특이사항 및 요구사항
    @JsonProperty(value = "hotel_room_pk")
    private int hotelRoomPk;
}
