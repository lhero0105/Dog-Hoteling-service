package com.green.hoteldog.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class MyDog {
    @JsonIgnore
    private int userDogPk;
    private int dogSize;
    private String dogNm;
    private String dogPic;
}
//승준
