package com.green.hoteldog.reservation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DogInfo {
    @JsonIgnore
    private Integer resPk;
    private int sizePk;
    private int age;
    private String information;
}
