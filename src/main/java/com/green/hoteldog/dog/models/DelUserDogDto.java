package com.green.hoteldog.dog.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DelUserDogDto {
    @JsonProperty(value = "user_pk")
    private int userPk;
    @JsonProperty(value = "user_dog_pk")
    private int userDogPk;
}
