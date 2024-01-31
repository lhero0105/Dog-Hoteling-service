package com.green.hoteldog.dog.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DelUserDogDto {
    @JsonIgnore
    private int userPk;
    @JsonProperty(value = "user_dog_pk")
    private int userDogPk;
}
