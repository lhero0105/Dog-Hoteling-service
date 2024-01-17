package com.green.hoteldog.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
//영웅
@Data
public class DogInfo {
    @JsonIgnore
    private Integer resPk;
    private int userDogInfo; // 유저 강이지 pk, 등록x 0
    private int sizePk;
    private String dogNm;
    private int dogAge;
    private String information; // 특이사항 및 요구사항
}
