package com.green.hoteldog.dog.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DelUserDogDto {
    @JsonIgnore
    private int userPk;
    private int userDogPk;
}
